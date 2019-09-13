package com.andymariscal.walmartproducts.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.andymariscal.walmartproducts.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(toolbar, navController, drawerLayout)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.shopping -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_productsFragment)
                    closeDrawer()
                    true
                }
                R.id.sourceCode -> {
                    startActivity(Intent(this, WebViewActivity::class.java))
                    true
                }
                R.id.webSite -> {
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walmart.com")).apply {
                        startActivity(this)
                    }
                    true
                }
                else -> true
            }
        }
    }

    fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    fun closeDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START)
    }
}