package com.example.passwordstorer.ui.pin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.passwordstorer.R
import com.example.passwordstorer.common.listeners.BiometricHelperListener
import com.example.passwordstorer.common.utils.*
import com.example.passwordstorer.databinding.FragmentSetUpPinBinding
import com.example.passwordstorer.room.entity.PinEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
@AndroidEntryPoint
class SetUpPinFragment : Fragment(R.layout.fragment_set_up_pin), View.OnClickListener,
    BiometricHelperListener {

    private val TAG = this::class.simpleName.toString()

    private lateinit var binding: FragmentSetUpPinBinding
    private lateinit var biometricHelper: BiometricHelper
    private val setUpPinViewModel: SetUpPinViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSetUpPinBinding.bind(view)
        setUpClickListeners()
        initViews()
    }

    private fun setUpClickListeners() {
        binding.rbCreatePin.setOnClickListener(this)
        binding.rbUseExistingPin.setOnClickListener(this)
    }

    private fun initViews() {
        binding.clSelectPinLayout.show()
        biometricHelper = getMainActivity()?.let { BiometricHelper(it, this) }!!
        if (!biometricHelper.isDeviceSecure()) {
            binding.tvUseExistingPin.hide()
            binding.rlOr.hide()
        } else {
            binding.tvUseExistingPin.show()
            binding.rlOr.show()
        }
    }

    override fun onClick(v: View) {
        when (v) {
            binding.rbCreatePin -> initSetUpPin()
            binding.rbUseExistingPin -> startSettingsScreenLockIntent()
            binding.btnSave -> savePinToDB()
        }
    }

    private fun startSettingsScreenLockIntent() {
        biometricHelper.authenticate()
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        Handler(Looper.getMainLooper()).postDelayed({
            toast(getString(R.string.authentication_success))
            updatePinAndBiometricAuthentication(false)
            navigateToDashboardScreen()
        }, 500)
    }

    override fun onAuthenticationErrorFailed(errorCode: Int?, errString: CharSequence?) {
        toast(getString(R.string.authentication_failed))
        binding.rbUseExistingPin.isChecked = !binding.rbUseExistingPin.isChecked
    }

    private fun initSetUpPin() {
        hideShowConfirPinViews(false)
        Handler(Looper.getMainLooper()).postDelayed({
            loadSetUpPinLayoutAnim()
            binding.clSelectPinLayout.hide()
            binding.clSetUpPin.show()
        }, 500)
        binding.btnSave.setOnClickListener(this)

        binding.setUpPin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvErrorText.hide()
                if (s?.length == 4) {
                    hideKeyboard()
                    hideShowConfirPinViews(true)
                    hideShowPinViews(false)
                    binding.confirmPin.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.confirmPin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 1) {
                    binding.tvErrorText.hide()
                } else if (s?.length == 4) {
                    hideKeyboard()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun hideShowConfirPinViews(show: Boolean) {
        if (show) {
            binding.btnSave.show()
            binding.confirmPin.show()
            binding.tvConfirmPin.show()
            loadConfirmViewSlideInAnim()
        } else {
            binding.btnSave.hide()
            binding.confirmPin.hide()
            binding.tvConfirmPin.hide()
        }
    }

    private fun loadSetUpPinLayoutAnim() {
        loadAnimation(binding.clSetUpPin, R.anim.slide_in_right)
        loadAnimation(binding.clSelectPinLayout, R.anim.slide_out_left)
    }

    private fun loadConfirmViewSlideInAnim() {
        loadAnimation(
            binding.tvConfirmPin,
            binding.confirmPin, binding.btnSave, animId = R.anim.slide_in_right
        )
        loadAnimation(
            binding.tvSetUp4Pin,
            binding.setUpPin, binding.tvErrorText, animId = R.anim.slide_out_left
        )
    }

    private fun loadPinViewSlideInAnim() {
        loadAnimation(
            binding.tvSetUp4Pin,
            binding.setUpPin, binding.tvErrorText, animId = R.anim.slide_in_right
        )
        loadAnimation(
            binding.tvConfirmPin,
            binding.confirmPin, binding.btnSave, animId = R.anim.slide_out_left
        )
    }

    private fun hideShowPinViews(show: Boolean) {
        if (show) {
            binding.setUpPin.show()
            binding.tvSetUp4Pin.show()
            loadPinViewSlideInAnim()
        } else {
            binding.setUpPin.hide()
            binding.tvSetUp4Pin.hide()
        }
    }

    private fun savePinToDB() {
        when {
            binding.confirmPin.isTextBlank() -> {
                binding.tvErrorText.text = getString(R.string.error_confirm_pin_blank)
                binding.tvErrorText.show()
                return
            }

            binding.setUpPin.text() != binding.confirmPin.text() -> {
                hideShowConfirPinViews(false)
                hideShowPinViews(true)
                binding.setUpPin.clearText()
                binding.confirmPin.clearText()
                binding.tvErrorText.text = getString(R.string.error_pin_do_match)
                binding.tvErrorText.show()
            }

            else -> {
                lifecycleScope.launch {
                    setUpInsertPinLiveData()
                    setUpPinViewModel.insertPin(PinEntity(binding.setUpPin.text.toString()))
                }
            }
        }
    }

    private fun setUpInsertPinLiveData() {
        setUpPinViewModel.insertResultLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (isInsertSuccessful(result)) {
                toast(getString(R.string.pin_saved_success))
                updatePinAndBiometricAuthentication(true)
                navigateToLoginScreen()
            } else toast(getString(R.string.pin_saved_failed))
        })
    }

    private fun updatePinAndBiometricAuthentication(isPinEnabled: Boolean) {
        setUpPinViewModel.updatePinAuthentication(isPinEnabled)
        setUpPinViewModel.updateBiometricAuthentication(!isPinEnabled)
    }

    private fun navigateToLoginScreen() {
        val actionLogin = SetUpPinFragmentDirections.actionSetUpPinFragmentToLoginFragment()
        findNavController().safeNavigate(actionLogin)
    }

    private fun navigateToDashboardScreen() {
        val actionDashboard = SetUpPinFragmentDirections.actionSetUpPinFragmentToHomeFragment()
        findNavController().safeNavigate(actionDashboard)
    }
}