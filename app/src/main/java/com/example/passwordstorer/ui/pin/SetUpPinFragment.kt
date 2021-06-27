package com.example.passwordstorer.ui.pin

import android.app.KeyguardManager
import android.content.Context.KEYGUARD_SERVICE
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.hide
import com.example.passwordstorer.common.utils.show
import com.example.passwordstorer.common.utils.text
import com.example.passwordstorer.common.utils.toast
import com.example.passwordstorer.databinding.FragmentSetUpPinBinding
import com.example.passwordstorer.room.entity.PinEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class SetUpPinFragment : Fragment(R.layout.fragment_set_up_pin), View.OnClickListener {

    private lateinit var binding: FragmentSetUpPinBinding
    private lateinit var keyguardManager: KeyguardManager
    private lateinit var biometricPrompt: BiometricPrompt

    private val setUpPinViewModel: SetUpPinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetUpPinBinding.bind(view)
        setUpClickListeners()
        initViews()
        //setupviewModel()
    }

    private fun setUpClickListeners() {
        binding.rbCreatePin.setOnClickListener(this)
        binding.rbUseExistingPin.setOnClickListener(this)
    }

    private fun initViews() {
        keyguardManager = requireContext().getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        if (!isDeviceSecure()) {
            binding.tvUseExistingPin.hide()
            binding.rlOr.hide()
        } else {
            binding.tvUseExistingPin.show()
            binding.rlOr.show()
        }
        biometricPrompt = createBiometricPrompt()
    }

    override fun onClick(v: View) {
        when (v) {
            binding.rbCreatePin -> initSetUpPin()
            binding.rbUseExistingPin -> startSettingsScreenLockIntent()
            binding.btnSave -> savePinToDB()
        }
    }

    // Just Checked here need to make more changes
    private fun setupviewModel() {
        lifecycleScope.launch {
            setUpPinViewModel.liveDataPin.observe(viewLifecycleOwner, Observer { pin ->
                toast(pin)
            })
        }
    }

    private fun initSetUpPin() {
        Handler(Looper.getMainLooper()).postDelayed({
            binding.clSelectPinLayout.hide()
            binding.clSetUpPin.show()
        }, 500)
        binding.btnSave.setOnClickListener(this)
    }

    private fun createBiometricPrompt(): BiometricPrompt {
        val executor = ContextCompat.getMainExecutor(requireContext())
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                toast(getString(R.string.authentication_failed))
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                toast(getString(R.string.authentication_success))
                navigateToDashboardScreen()
            }

            override fun onAuthenticationFailed() {
                toast(getString(R.string.authentication_failed))
            }
        }
        return BiometricPrompt(this, executor, callback)
    }

    private fun createPromptInfo(): BiometricPrompt.PromptInfo {
        return BiometricPrompt.PromptInfo.Builder().run {
            setTitle(getString(R.string.authentication_required))
            setDescription(getString(R.string.pin_pattern_password))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setAllowedAuthenticators(BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            } else {
                setDeviceCredentialAllowed(true)
            }
            build()
        }
    }

    private fun isDeviceSecure(): Boolean {
        return keyguardManager.isDeviceSecure
    }

    private fun startSettingsScreenLockIntent() {
        val promptInfo = createPromptInfo()
        biometricPrompt.authenticate(promptInfo)
    }

    private fun savePinToDB() {
        if (binding.setUpPin.text() == binding.confirmPin.text()) {
            lifecycleScope.launch {
                setUpInsertPinLiveData()
                setUpPinViewModel.insertPin(PinEntity(binding.setUpPin.text.toString()))
            }
        } else {
            binding.setUpPin.clearText()
            binding.confirmPin.clearText()
            binding.tvErrorText.show()
        }
    }

    private fun setUpInsertPinLiveData() {
        setUpPinViewModel.insertResultLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (result != -1L) {
                toast(getString(R.string.pin_saved_success))
                navigateToLoginScreen()
            } else toast(getString(R.string.pin_saved_failed))
        })
    }

    private fun navigateToLoginScreen() {
        setUpPinViewModel.updatePinAuthentication(true)
        val actionLogin = SetUpPinFragmentDirections.actionSetUpPinFragmentToLoginFragment()
        Navigation.findNavController(binding.root).navigate(actionLogin)
    }

    private fun navigateToDashboardScreen() {
        setUpPinViewModel.updateBiometricAuthentication(true)
        val actionDashboard = SetUpPinFragmentDirections.actionSetUpPinFragmentToDashboardFragment()
        Navigation.findNavController(binding.root).navigate(actionDashboard)
    }
}