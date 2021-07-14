package com.example.passwordstorer.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.passwordstorer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController = (supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
//                as NavHostFragment).navController
//
//        val appBarConfiguration = AppBarConfiguration(
//            navController.graph)
//        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

//    override fun finish() {
//        super.finish()
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
//    }
}