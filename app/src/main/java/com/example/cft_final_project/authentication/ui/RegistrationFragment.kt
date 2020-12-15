package com.example.cft_final_project.authentication.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.R
import com.example.cft_final_project.authentication.data.sources.network.AuthParams
import com.example.cft_final_project.common.presentation.SnackbarManager
import com.example.cft_final_project.common.util.EventObserver
import com.example.cft_final_project.common.util.delegates.autoCleared
import com.example.cft_final_project.common.util.delegates.snackbarManager
import com.example.cft_final_project.common.util.extensions.hideKeyboard
import com.example.cft_final_project.common.util.extensions.onActionDoneHideKeyboard
import com.example.cft_final_project.common.util.extensions.textChanges
import com.example.cft_final_project.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val authViewModel by sharedViewModel<AuthViewModel>()

    private var binding: FragmentRegistrationBinding by autoCleared()
    private var snackbarManager: SnackbarManager by snackbarManager()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentRegistrationBinding.bind(view).apply {
            passwordInput.onActionDoneHideKeyboard()

            signUpBtn.setOnClickListener {
                val name = nameInput.text.toString()
                val password = passwordInput.text.toString()

                if (name.isNotBlank() && password.isNotBlank()) {
                    requireView().hideKeyboard()
                    val credentials = AuthParams(name, password)
                    authViewModel.attemptRegistration(credentials)
                } else {
                    snackbarManager.showMessage(R.string.empty_fields)
                }
            }
        }

        observeUi()
        observeNavEvent()
        observeErrorEvent()
    }

    override fun onStart() {
        super.onStart()
        disableButtonUntilValidInput()
    }

    private fun disableButtonUntilValidInput() {
        val isNameValidFlow = binding.nameInput.textChanges()
            .map { it.isNotBlank() && it.trim().length > 3 }
            .distinctUntilChanged()

        val isPassValidFlow = binding.passwordInput.textChanges()
            .map { it.isNotBlank() && it.trim().length > 3 }
            .distinctUntilChanged()

        combine(isNameValidFlow, isPassValidFlow) { isNameValid, isPassValid ->
            isNameValid && isPassValid
        }
            .onEach { binding.signUpBtn.isEnabled = it }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeUi() {
        authViewModel.isLoadingLiveData.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.signUpBtn.isInvisible = isLoading
        })
    }

    private fun observeNavEvent() {
        authViewModel.toLoanListEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_registrationFragment_to_loanListFragment)
        })
    }

    private fun observeErrorEvent() {
        authViewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            snackbarManager.showError(it)
        })
    }
}