package com.example.passwordstorer.common.listeners

import androidx.biometric.BiometricPrompt

interface BiometricHelperListener {

    fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult)

    fun onAuthenticationErrorFailed(errorCode: Int? = null, errString: CharSequence? = null)
}