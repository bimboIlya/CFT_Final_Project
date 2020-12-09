package com.example.cft_final_project.loans.ui.loan_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cft_final_project.MainActivity
import com.example.cft_final_project.R
import com.example.cft_final_project.common.util.delegates.autoCleared
import com.example.cft_final_project.databinding.FragmentLoanDetailsBinding
import com.example.cft_final_project.loans.data.model.LoanStatus.*
import com.example.cft_final_project.loans.data.model.LoanUi
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoanDetailsFragment : Fragment(R.layout.fragment_loan_details) {

    private val loanViewModel: LoanDetailsViewModel by viewModel(state = { requireArguments() })

    private var binding: FragmentLoanDetailsBinding by autoCleared()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoanDetailsBinding.bind(view)

        observeUi()
    }

    private fun observeUi() {
        loanViewModel.loanLiveData.observe(viewLifecycleOwner) { loan ->
            (requireActivity() as MainActivity)
                .supportActionBar?.title = getString(R.string.loan, loan.id)

            setUiAccordingToLoanStatus(loan)
        }

    }

    private fun setUiAccordingToLoanStatus(loan: LoanUi) = with(binding) {
        when (loan.status) {
            APPROVED -> { instructions.setText(R.string.instruction_approved) }
            REGISTERED -> { instructions.setText(R.string.instruction_registered) }
            REJECTED -> { instructions.setText(R.string.instruction_rejected) }
        }
        loanDate.text = loan.date
        debtorName.text = getString(R.string.debtor_name, loan.lastName, loan.firstName)
        debtorPhoneNumber.text = getString(R.string.debtor_phone_number, loan.phoneNumber)
        loanAmount.text = getString(R.string.loan_amount, loan.amount)
        loanPercent.text = getString(R.string.loan_percent, loan.percent)
        loanPeriod.text = getString(R.string.loan_period, loan.period)
    }
}