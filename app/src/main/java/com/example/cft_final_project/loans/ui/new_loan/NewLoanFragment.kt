package com.example.cft_final_project.loans.ui.new_loan

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.R
import com.example.cft_final_project.common.presentation.SnackbarManager
import com.example.cft_final_project.common.util.EventObserver
import com.example.cft_final_project.common.util.delegates.autoCleared
import com.example.cft_final_project.common.util.delegates.snackbarManager
import com.example.cft_final_project.common.util.extensions.onActionDoneHideKeyboard
import com.example.cft_final_project.common.util.extensions.textChanges
import com.example.cft_final_project.databinding.FragmentNewLoanBinding
import com.example.cft_final_project.loans.data.model.LoanUi
import com.example.cft_final_project.loans.data.network.LoanRequestParams
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

@OptIn(FlowPreview::class)
class NewLoanFragment : Fragment(R.layout.fragment_new_loan) {

    private val loanViewModel by viewModel<NewLoanViewModel>()

    private var binding: FragmentNewLoanBinding by autoCleared()
    private var snackbarManager: SnackbarManager by snackbarManager()

    private var maxAmount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNewLoanBinding.bind(view).apply {
            amountLayout.helperText = getString(R.string.max_amount, maxAmount)
            amountInput.onActionDoneHideKeyboard()
            applyNumberMaskToPhoneInput(phoneNumberInput)

            requestLoanBtn.setOnClickListener {
                val loanParams = LoanRequestParams(
                    amount = amountInput.text.toString().toInt(),
                    firstName = firstNameInput.text.toString(),
                    lastName = lastNameInput.text.toString(),
                    percent = percentInput.text.toString().toDouble(),
                    period = periodInput.text.toString().toInt(),
                    phoneNumber = phoneNumberInput.text.toString()
                )
                loanViewModel.attemptToCreateLoan(loanParams)
            }
        }

        observeUi()
        observeNavEvents()
        observeErrorEvents()

        disableButtonUntilValidInput()
    }

    private fun applyNumberMaskToPhoneInput(phoneInput: EditText) {
        val slots = UnderscoreDigitSlotsParser().parseSlots("_ (___) ___-__-__")
        val phoneMask = MaskImpl.createTerminated(slots)
        MaskFormatWatcher(phoneMask).installOn(phoneInput)
    }

    private fun disableButtonUntilValidInput() {
        val isFirstNameValidFlow = binding.firstNameInput.textChanges()
            .map { it.isNotBlank() }
            .distinctUntilChanged()

        val isLastNameValidFlow = binding.lastNameInput.textChanges()
            .map { it.isNotBlank() }
            .distinctUntilChanged()

        val isPhoneNumberValidFlow = binding.phoneNumberInput.textChanges()
            .map { it.isNotBlank() && it.length == 17 }
            .distinctUntilChanged()

        val isAmountValidFlow = binding.amountInput.textChanges()
            .map { it.isNotBlank() && it.toString().toInt() in 1..maxAmount }
            .distinctUntilChanged()

        combine(
            isFirstNameValidFlow, isLastNameValidFlow, isPhoneNumberValidFlow, isAmountValidFlow
        ) { isFirstNameValid, isLastNameValid, isPhoneNumberValid, isAmountValid ->
            isFirstNameValid && isLastNameValid && isPhoneNumberValid && isAmountValid
        }
            .onEach { binding.requestLoanBtn.isEnabled = it }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeUi() {
        loanViewModel.loanConditionsLiveData.observe(viewLifecycleOwner, { loanConditions ->
            maxAmount = loanConditions.maxAmount
            with(binding) {
                percentInput.setText(loanConditions.percent.toString())
                periodInput.setText(loanConditions.period.toString())
                amountLayout.helperText = getString(R.string.max_amount, maxAmount)
            }
        })

        loanViewModel.isLoadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.requestLoanBtn.isInvisible = isLoading
        })

        binding.amountInput.textChanges()
            .debounce(16)
            .onEach { input ->
                when {
                    input.isBlank() -> binding.amountLayout.isErrorEnabled = true
                    input.toString().toInt() == 0 ->
                        binding.amountLayout.error = getString(R.string.amount_is_zero)
                    input.toString().toInt() > maxAmount ->
                        binding.amountLayout.error =
                            getString(R.string.amount_is_greater_than_max, maxAmount)
                    else -> binding.amountLayout.isErrorEnabled = false
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeErrorEvents() {
        loanViewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            snackbarManager.showError(it)
        })
    }

    private fun observeNavEvents() {
        loanViewModel.toLoanRequestResultEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToLoanRequestResult(it)
        })
    }

    private fun navigateToLoanRequestResult(loan: LoanUi) {
        val args = bundleOf(NEW_LOAN_REQUEST_KEY to loan)
        findNavController().navigate(R.id.action_newLoanFragment_to_newLoanResultFragment, args)
    }


    companion object {
        const val NEW_LOAN_REQUEST_KEY = "new_loan_request"
    }
}