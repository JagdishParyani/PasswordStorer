package com.example.passwordstorer.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.eLog
import com.example.passwordstorer.common.utils.safeNavigate
import com.example.passwordstorer.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val TAG = this::class.simpleName.toString()
    private lateinit var binding: FragmentSplashBinding
    private val splashViewModel: SplashViewModel by viewModels()

    private var pinSetUpResult = false
    private var biometricSetUpResult = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        lifecycleScope.launch {
            splashViewModel.apply {

//                isPinSetUpLiveData.removeObservers(viewLifecycleOwner)
                isPinSetUpLiveData.observe(viewLifecycleOwner, { pinSetUpLiveDataResult ->
                    setPinSetUpBoolValue(pinSetUpLiveDataResult)
                })

//                isBiometricSetUpLiveData.removeObservers(viewLifecycleOwner)
                isBiometricSetUpLiveData.observe(
                    viewLifecycleOwner, { biometricSetUpLiveDataResult ->
                        setBiometricBoolValue(biometricSetUpLiveDataResult)
                    })
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            updateNavigation()
        }, 5000)
    }

    private fun setBiometricBoolValue(biometricSetUpLiveDataResult: Boolean) {
        this.biometricSetUpResult = biometricSetUpLiveDataResult
    }

    private fun getBiometricBoolValue(): Boolean {
        return biometricSetUpResult
    }

    private fun setPinSetUpBoolValue(pinSetUpLiveDataResult: Boolean) {
        this.pinSetUpResult = pinSetUpLiveDataResult
    }

    private fun getPinSetUpBoolValue(): Boolean {
        return pinSetUpResult
    }

    private fun updateNavigation() {
        val pinSetUpResult: Boolean = getPinSetUpBoolValue()
        val biometricSetUpResult: Boolean = getBiometricBoolValue()
        TAG.eLog("pinSetUpResult: $pinSetUpResult -> biometricSetUpResult: $biometricSetUpResult")
        var navDirections: NavDirections? = null
        if (!pinSetUpResult && !biometricSetUpResult) {
            navDirections = SplashFragmentDirections.actionSplashFragmentToSetUpPinFragment()
        } else if (pinSetUpResult) {
            navDirections = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
        } else if (biometricSetUpResult) {
            startBiometricAuthentication()
        }
        if (navDirections != null) {
            findNavController().safeNavigate(navDirections)
        }
    }

    private fun startBiometricAuthentication() {
        // TODO make Biometric class & start authentication
        // TODO On success navigate to Dashboard Fragment
    }
}