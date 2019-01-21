package com.b.nsofronovic.bitcoinwallet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.b.nsofronovic.bitcoinwallet.R

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val host = NavHostFragment.create(R.navigation.navigation_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, host)
            .setPrimaryNavigationFragment(host)
            .commit()

    }
}
