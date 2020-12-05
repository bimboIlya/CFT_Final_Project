package com.example.cft_final_project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cft_final_project.common.AuthManager
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val authManager: AuthManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (authManager.hasAccessToken()) {
            false -> findNavController().navigate(R.id.action_homeFragment_to_guestUserFragment)
            true -> findNavController().navigate(R.id.action_homeFragment_to_loanListFragment)
        }
    }
}