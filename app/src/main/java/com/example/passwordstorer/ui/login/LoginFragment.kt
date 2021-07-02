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
class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        binding.loginPin.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvErrorText.hide()
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
        binding.loginPin.clearText()
        binding.tvErrorText.show()
    }

    private fun navigateToDashBoard() {
        val navDirections = LoginFragmentDirections.actionLoginFragmentToDashboardFragment()
        findNavController().safeNavigate(navDirections)
    }
}