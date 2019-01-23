package com.b.nsofronovic.bitcoinwallet.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.b.nsofronovic.bitcoinwallet.R
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        //window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        navHostFragment = NavHostFragment.create(R.navigation.navigation_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        NavigationUI.setupWithNavController(bottom_navigation, navHostFragment.navController)
    }

    fun showNavigation() {
        bottom_navigation.visibility = View.VISIBLE
    }
}
