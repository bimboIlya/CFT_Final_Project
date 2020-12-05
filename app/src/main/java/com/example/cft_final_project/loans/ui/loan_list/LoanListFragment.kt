package com.example.cft_final_project.loans.ui.loan_list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.R
import com.example.cft_final_project.common.AuthManager
import com.example.cft_final_project.common.util.EventObserver
import com.example.cft_final_project.databinding.FragmentLoanListBinding
import com.example.cft_final_project.loans.data.model.LoanUi
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class LoanListFragment : Fragment(R.layout.fragment_loan_list) {

    private var _binding: FragmentLoanListBinding? = null
    private val binding: FragmentLoanListBinding get() = _binding!!

    private val loanViewModel: LoanListViewModel by viewModel()
    private val authManager: AuthManager by inject()

    private lateinit var adapter: LoanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoanListBinding.bind(view).apply {
            loanList.adapter = LoanAdapter(loanViewModel).also { adapter = it }
            lifecycleOwner = viewLifecycleOwner
        }

        observeUi()
        observeNavigation()
    }

    private fun observeUi() {
        loanViewModel.loanListLiveData.observe(viewLifecycleOwner, {
            binding.emptyStub.isVisible = it.isEmpty()
            adapter.submitList(it)
        })
    }

    private fun observeNavigation() {
        loanViewModel.toLoanDetailEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToOrderDetails(it)
        })
    }

    private fun navigateToOrderDetails(loan: LoanUi) {
        val args = bundleOf(LOAN_KEY to loan)
        findNavController().navigate(R.id.action_loanListFragment_to_loanDetailsFragment, args)
    }

    private fun navigateToNewLoan() {
//        findNavController().navigate() // todo
    }

    private fun navigateToGuestFragment() {
        findNavController().navigate(R.id.action_loanListFragment_to_guestUserFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_loan_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_log_out -> {
                authManager.invalidateToken()
                navigateToGuestFragment()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    companion object {
        const val LOAN_KEY = "loan_key"
    }
}