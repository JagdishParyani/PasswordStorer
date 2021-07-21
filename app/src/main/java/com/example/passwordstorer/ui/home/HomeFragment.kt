package com.example.passwordstorer.ui.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.*
import com.example.passwordstorer.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var binding: FragmentHomeBinding
    lateinit var childNavController: NavController
    private val TAG = this::class.simpleName.toString()
    val timeInterval = 2000
    var mBackPressed = 0L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initViews()
    }

    private fun initViews() {
        binding.bottomNavigationView.apply {
            enableAnimation(false)
            setRippleColor(ColorStateList.valueOf(getColor(R.color.item_selected)))
            disableTooltipText()
            setTypeface(getCustomTypeFace(getString(R.string.poppins_regular)))
            setIconSize(22f)
            setTextSize(14f)
        }
        val childNavHostFragment = childFragmentManager.fragments.first() as NavHostFragment
        childNavController = childNavHostFragment.findNavController()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val isNavigated = NavigationUI.onNavDestinationSelected(item, childNavController)
            TAG.eLog("$isNavigated")
            return@setOnNavigationItemSelectedListener true
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mBackPressed + timeInterval > System.currentTimeMillis()) {
                    val boolean = childNavController.popBackStack()
                    if (!boolean) {
                        getMainActivity()?.finish()
                    }
                    return
                } else {
                    toast(getString(R.string.click_again_to_exit))
                }
                mBackPressed = System.currentTimeMillis()
            }
        }
        getMainActivity()?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }
}