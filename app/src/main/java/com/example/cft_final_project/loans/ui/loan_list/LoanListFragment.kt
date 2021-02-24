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
import com.example.cft_final_project.common.presentation.SnackbarManager
import com.example.cft_final_project.common.presentation.showTranslatePopUpMenu
import com.example.cft_final_project.common.util.EventObserver
import com.example.cft_final_project.common.util.delegates.autoCleared
import com.example.cft_final_project.common.util.delegates.snackbarManager
import com.example.cft_final_project.databinding.FragmentLoanListBinding
import com.example.cft_final_project.loans.data.model.LoanUi
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoanListFragment : Fragment(R.layout.fragment_loan_list) {

    private val loanViewModel: LoanListViewModel by viewModel()

    private var binding: FragmentLoanListBinding by autoCleared()
    private var adapter: LoanAdapter by autoCleared()
    private var snackbarManager: SnackbarManager by snackbarManager(R.id.fab)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentLoanListBinding.bind(view).apply {
            loanList.adapter = LoanAdapter(loanViewModel).also { adapter = it }

            fab.setOnClickListener {
                navigateToNewLoanFragment()
            }
        }

        setUpPullToRefresh()

        observeUi()
        observeNavEvents()
        observeErrorEvents()
    }

    private fun setUpPullToRefresh() = with(binding.pullToRefresh) {
        setOnRefreshListener { loanViewModel.retry() }
        setColorSchemeResources(R.color.orange)
    }

    private fun observeUi() {
        loanViewModel.loanListLiveData.observe(viewLifecycleOwner, { loanList ->
            binding.emptyStub.isVisible = loanList.isEmpty()
            adapter.submitList(loanList)
        })

        loanViewModel.isLoadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            binding.pullToRefresh.isRefreshing = isLoading
        })
    }

    private fun observeErrorEvents() {
        loanViewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            snackbarManager.showError(it)
        })
    }

    private fun observeNavEvents() {
        loanViewModel.toLoanDetailEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToOrderDetails(it)
        })

        loanViewModel.toGuestScreenEvent.observe(viewLifecycleOwner, EventObserver {
            navigateToGuestFragment()
        })
    }

    private fun navigateToOrderDetails(loan: LoanUi) {
        val args = bundleOf(LOAN_KEY to loan)
        findNavController().navigate(R.id.action_loanListFragment_to_loanDetailsFragment, args)
    }

    private fun navigateToNewLoanFragment() {
        findNavController().navigate(R.id.action_loanListFragment_to_newLoanFragment)
    }

    private fun navigateToGuestFragment() {
        findNavController().navigate(R.id.action_loanListFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_loan_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_log_out -> {
                loanViewModel.clearCachedLoans()
                true
            }
            R.id.menu_translate -> {
                showTranslatePopUpMenu()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    companion object {
        const val LOAN_KEY = "loan_key"
    }
}