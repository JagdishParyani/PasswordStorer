package com.example.passwordstorer.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.hide
import com.example.passwordstorer.common.utils.safeNavigate
import com.example.passwordstorer.common.utils.show
import com.example.passwordstorer.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        initViews()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding.btnForgotPin.setOnClickListener(this)
    }

    private fun initViews() {
        binding.includeLoginPin.tvScreenPinTitle.text = getString(R.string.enter_login_pin)
        binding.includeLoginPin.pinView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                hideViews()
                if (s?.length == 4) {
                    loginViewModel.isPinEqual(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        lifecycleScope.launch {
            loginViewModel.getPinLiveData.observe(viewLifecycleOwner, {
                if (it) navigateToDashBoard() else clearPin()
            })
        }
    }

    private fun clearPin() {
        binding.includeLoginPin.pinView.clearText()
        showViews()
    }

    private fun hideViews(){
        binding.tvErrorText.hide()
        binding.btnForgotPin.hide()
    }

    private fun showViews(){
        binding.tvErrorText.show()
        binding.btnForgotPin.show()
    }

    private fun navigateToDashBoard() {
        val navDirections = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
        findNavController().safeNavigate(navDirections)
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnForgotPin -> navigateToForgotPin()
        }
    }

    private fun navigateToForgotPin() {
        val navDirections = LoginFragmentDirections.actionLoginFragmentToForgotPinFragment()
        findNavController().safeNavigate(navDirections)
    }
}