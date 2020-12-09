package com.example.cft_final_project.loans.ui.new_loan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.R
import com.example.cft_final_project.common.util.delegates.autoCleared
import com.example.cft_final_project.databinding.FragmentNewLoanResultBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewLoanResultFragment : Fragment(R.layout.fragment_new_loan_result) {

    private val loanViewModel by viewModel<NewLoanResultViewModel>(state = { requireArguments() })

    private var binding: FragmentNewLoanResultBinding by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentNewLoanResultBinding.bind(view).apply {
            returnBtn.setOnClickListener {
                navigateToLoanListFragment()
            }
        }

        observeUi()
    }

    private fun observeUi() {
        loanViewModel.loanLiveData.observe(viewLifecycleOwner, { loan ->
            with(binding) {
                receiver.text = getString(R.string.receiver, loan.firstName, loan.lastName)
                phoneNumber.text = getString(R.string.debtor_phone_number, loan.phoneNumber)
                result.text =
                    getString(R.string.result_registered, loan.amount, loan.percent, loan.period)
            }
        })
    }

    private fun navigateToLoanListFragment() {
        findNavController().navigate(R.id.action_newLoanResultFragment_to_loanListFragment)
    }
}