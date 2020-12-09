package com.example.cft_final_project

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cft_final_project.common.util.extensions.hideKeyboard

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host)
        setUpToolbar()
    }

    private fun setUpToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))

        appBarConfiguration =
            AppBarConfiguration.Builder(R.id.guestUserFragment, R.id.loanListFragment).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        findViewById<View>(R.id.nav_host).hideKeyboard()
        return navController.navigateUp(appBarConfiguration) ||
                super.onSupportNavigateUp()
    }
}