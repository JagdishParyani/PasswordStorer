package com.example.passwordstorer.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.passwordstorer.R
import com.example.passwordstorer.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    lateinit var binding: FragmentDashboardBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        
    }
}