package com.example.passwordstorer.common.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.passwordstorer.R

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: CharSequence) = requireContext().toast(message)

fun Fragment.loadAnimation(view: View, animId: Int){
    view.startAnimation(AnimationUtils.loadAnimation(requireContext(), animId))
}

fun Fragment.loadAnimation(vararg views: View, animId: Int){
    for (view in views) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), animId))
    }
}