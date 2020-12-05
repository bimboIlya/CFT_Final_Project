package com.example.cft_final_project.authentication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.R
import com.example.cft_final_project.common.util.EventObserver
import com.example.cft_final_project.databinding.FragmentRegistrationBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val authViewModel by sharedViewModel<AuthViewModel>()

    private var _binding: FragmentRegistrationBinding? = null
    private val binding: FragmentRegistrationBinding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentRegistrationBinding.bind(view).apply {
            lifecycleOwner = viewLifecycleOwner

            signUpBtn.setOnClickListener {
                val name = nameInput.text.toString()
                val password = passwordInput.text.toString()

                if (name.isNotEmpty() && password.isNotEmpty()) {
                    authViewModel.attemptRegistration(name, password)
                } else {
                    Snackbar.make(root, R.string.empty_fields, Snackbar.LENGTH_SHORT).show()
                }
            }
        }

        observeNavigationEvent()
        observeErrorEvent()
    }

    private fun observeNavigationEvent() {
        authViewModel.toLoanListEvent.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_registrationFragment_to_loanListFragment)
        })
    }

    private fun observeErrorEvent() {
        authViewModel.errorEvent.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(requireView(), it.errorMessageId, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}