package com.example.passwordstorer.ui.forgotpin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.text
import com.example.passwordstorer.common.utils.toast
import com.example.passwordstorer.databinding.FragmentForgotPinBinding
import com.example.passwordstorer.room.entity.PinEntity
import com.example.passwordstorer.ui.pin.SetUpPinFragmentDirections
import kotlinx.coroutines.launch

class ForgotPinFragment : Fragment(R.layout.fragment_forgot_pin) {

    lateinit var binding: FragmentForgotPinBinding
    private val forgotPinViewModel by viewModels<ForgotPinViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentForgotPinBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        binding.includeForgotPin.tvScreenPinTitle.text = getString(R.string.reset_pin)
        binding.includeForgotPin.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 4){
                    resetPinToDB()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun resetPinToDB() {
        lifecycleScope.launch {
            binding.includeForgotPin.pinView.text()
            resetPinLiveData()
            forgotPinViewModel.insertPin(PinEntity(binding.includeForgotPin.pinView.text()))
        }
    }

    private fun resetPinLiveData() {
        forgotPinViewModel.insertResultLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (result != -1L) {
                toast(getString(R.string.pin_reset_success))
//                updatePinAndBiometricAuthentication(true)
//                navigateToLoginScreen()
            } else toast(getString(R.string.pin_saved_failed))
        })
    }

//    private fun updatePinAndBiometricAuthentication(isPinEnabled: Boolean) {
//        forgotPinViewModel.updatePinAuthentication(isPinEnabled)
//        forgotPinViewModel.updateBiometricAuthentication(!isPinEnabled)
//    }

//    private fun navigateToLoginScreen() {
//        val actionLogin = SetUpPinFragmentDirections.actionSetUpPinFragmentToLoginFragment()
//        Navigation.findNavController(binding.root).navigate(actionLogin)
//    }

//    private fun navigateToDashboardScreen() {
//        val actionDashboard = SetUpPinFragmentDirections.actionSetUpPinFragmentToDashboardFragment()
//        Navigation.findNavController(binding.root).navigate(actionDashboard)
//    }

}