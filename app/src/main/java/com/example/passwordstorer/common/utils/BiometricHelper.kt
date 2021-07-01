package com.example.passwordstorer.common.utils

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.passwordstorer.R
import com.example.passwordstorer.common.listeners.BiometricHelperListener

class BiometricHelper(
    private val activity: FragmentActivity,
    private val biometricHelperListener: BiometricHelperListener
) {

    private var keyguardManager: KeyguardManager =
        activity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
    private var biometricPrompt: BiometricPrompt

    init {
        biometricPrompt = createBiometricPrompt()
    }

    fun isDeviceSecure(): Boolean {
        return keyguardManager.isDeviceSecure
    }

    fun authenticate() {
        biometricPrompt.authenticate(createPromptInfo())
    }

    private fun createBiometricPrompt(): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(activity)
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                biometricHelperListener.onAuthenticationErrorFailed(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                biometricHelperListener.onAuthenticationSucceeded(result)
            }

            override fun onAuthenticationFailed() {
                biometricHelperListener.onAuthenticationErrorFailed()
            }
        }
        return BiometricPrompt(activity, executor, callback)
    }

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder().run {
            setTitle(activity.getString(R.string.authentication_required))
            setDescription(activity.getString(R.string.pin_pattern_password))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setAllowedAuthenticators(BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            } else {
                setDeviceCredentialAllowed(true)
            }
            build()
        }
    }
}