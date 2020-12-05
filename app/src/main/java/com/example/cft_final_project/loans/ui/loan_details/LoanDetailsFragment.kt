package com.example.cft_final_project.loans.ui.loan_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cft_final_project.MainActivity
import com.example.cft_final_project.R
import com.example.cft_final_project.databinding.FragmentLoanDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoanDetailsFragment : Fragment(R.layout.fragment_loan_details) {

    private val loanViewModel: LoanDetailsViewModel by viewModel(state = { requireArguments() })

    private var _binding: FragmentLoanDetailsBinding? = null
    private val binding: FragmentLoanDetailsBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoanDetailsBinding.bind(view).apply {

        }

        (requireActivity() as MainActivity)
            .supportActionBar?.title = getString(R.string.loan, loanViewModel.loanId)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}