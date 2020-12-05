package com.example.cft_final_project.loans.ui.new_loan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cft_final_project.R
import com.example.cft_final_project.databinding.FragmentLoanDetailsBinding
import com.example.cft_final_project.databinding.FragmentNewLoanBinding
import com.example.cft_final_project.loans.ui.loan_details.LoanDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewLoanFragment : Fragment(R.layout.fragment_new_loan) {

    private var _binding: FragmentNewLoanBinding? = null
    private val binding: FragmentNewLoanBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentNewLoanBinding.bind(view).apply {

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}