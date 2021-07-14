package com.example.passwordstorer.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.passwordstorer.R
import com.example.passwordstorer.common.customviews.TextDrawable
import com.example.passwordstorer.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initViews()
    }

    private fun initViews() {
//        val menu = binding.bottomNavigationView.menu

//        val dashboardItem = menu.findItem(R.id.item_dashboard)
//        val dashboardIcon = TextDrawable(requireContext())
//        dashboardIcon.setTextSize(size = R.dimen._10ssp.toFloat())
//        dashboardIcon.text = getString(R.string.add_icon)
//        dashboardItem.icon = dashboardIcon
    }
}