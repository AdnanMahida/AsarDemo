package com.ad.asardemo.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.ad.asardemo.R
import com.ad.asardemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment.newInstance()
    private val portFolioFragment = EmptyFragment.newInstance("PortFolio")
    private val walletFragment = EmptyFragment.newInstance("Wallet")
    private val profileFragment = EmptyFragment.newInstance("Profile")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpNavigation()
    }

    private fun setUpNavigation() {
        setCurrentFragment(homeFragment)

        binding.bottomNav.apply {
            ivHome.setOnClickListener(menuClick)
            ivPortfolio.setOnClickListener(menuClick)
            ivWallet.setOnClickListener(menuClick)
            ivProfile.setOnClickListener(menuClick)
            tvHome.setOnClickListener(menuClick)
            tvPortfolio.setOnClickListener(menuClick)
            tvWallet.setOnClickListener(menuClick)
            tvProfile.setOnClickListener(menuClick)
        }
    }

    private val menuClick = View.OnClickListener {
        when (it.id) {
            R.id.ivHome, R.id.tvHome -> setCurrentFragment(homeFragment)
            R.id.ivPortfolio, R.id.tvPortfolio -> setCurrentFragment(portFolioFragment)
            R.id.ivWallet, R.id.tvWallet -> setCurrentFragment(walletFragment)
            R.id.ivProfile, R.id.tvProfile -> setCurrentFragment(profileFragment)
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}