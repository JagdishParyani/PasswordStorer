package com.example.passwordstorer.common.utils

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.passwordstorer.R

class BiometricManager(private val context: Context) {
//
//    private var keyguardManager: KeyguardManager
//    private var biometricPrompt: BiometricPrompt
//
//    init {
//        keyguardManager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//        biometricPrompt = createBiometricPrompt()
//    }
//
//    fun isDeviceSecure(): Boolean {
//        return keyguardManager.isDeviceSecure
//    }
//
//    private fun createBiometricPrompt(): BiometricPrompt {
//        val executor = ContextCompat.getMainExecutor(context)
//        val callback = object : BiometricPrompt.AuthenticationCallback() {
//            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
//                context.toast(context.getString(R.string.authentication_failed))
//            }
//
//            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
//                context.toast(context.getString(R.string.authentication_success))
////                navigateToDashboardScreen()
//            }
//
//            override fun onAuthenticationFailed() {
//                context.toast(context.getString(R.string.authentication_failed))
//            }
//        }
//        return BiometricPrompt(this, executor, callback)
//    }
//
//    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
//        return BiometricPrompt.PromptInfo.Builder().run {
//            setTitle(context.getString(R.string.authentication_required))
//            setDescription(context.getString(R.string.pin_pattern_password))
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                setAllowedAuthenticators(BiometricManager.Authenticators.DEVICE_CREDENTIAL)
//            } else {
//                setDeviceCredentialAllowed(true)
//            }
//            build()
//        }
//    }
}