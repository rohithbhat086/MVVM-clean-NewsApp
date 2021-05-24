package com.rgb.example.newssampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.rgb.example.newssampleapp.databinding.ActivityMainBinding
import com.rgb.example.newssampleapp.presentation.viewmodel.MainViewModel
import com.rgb.example.newssampleapp.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityMainBinding
    @Inject
    lateinit var vmFactory: MainViewModelFactory
    lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupBottomMenu()

        mViewModel = ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)
    }

    private fun setupBottomMenu(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_host) as NavHostFragment
        mBinding.bottomMenu.setupWithNavController(navHostFragment.navController)
    }
}