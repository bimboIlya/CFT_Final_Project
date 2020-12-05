package com.example.cft_final_project.authentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.R
import com.example.cft_final_project.databinding.FragmentGuestUserBinding

class GuestUserFragment : Fragment(R.layout.fragment_guest_user) {

    private var _binding: FragmentGuestUserBinding? = null
    private val binding: FragmentGuestUserBinding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentGuestUserBinding.bind(view).apply {
            loginBtn.setOnClickListener {
                findNavController().navigate(R.id.action_guestUserFragment_to_loginFragment)
            }

            signUpBtn.setOnClickListener {
                findNavController().navigate(R.id.action_guestUserFragment_to_registrationFragment)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}