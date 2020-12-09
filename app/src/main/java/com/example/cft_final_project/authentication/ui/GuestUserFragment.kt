package com.example.cft_final_project.authentication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.R
import com.example.cft_final_project.common.util.delegates.autoCleared
import com.example.cft_final_project.databinding.FragmentGuestUserBinding

class GuestUserFragment : Fragment(R.layout.fragment_guest_user) {

    private var binding: FragmentGuestUserBinding by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentGuestUserBinding.bind(view).apply {
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_guestUserFragment_to_loginFragment)
            }

            signUpBtn.setOnClickListener {
                findNavController().navigate(R.id.action_guestUserFragment_to_registrationFragment)
            }
        }
    }
}