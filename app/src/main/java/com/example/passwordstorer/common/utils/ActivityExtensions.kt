package com.example.passwordstorer.common.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.passwordstorer.ui.main.MainActivity

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.getColor(colorId: Int): Int {
    return ContextCompat.getColor(requireContext(), colorId)
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Fragment.getMainActivity(): MainActivity? {
    return if (isAdded) {
        requireActivity() as MainActivity
    } else null
}

fun Fragment.getStringArray(id: Int): Array<out String> {
    return resources.getStringArray(id)
}

fun Fragment.getIntArray(id: Int): IntArray {
    return resources.getIntArray(id)
}

fun isInsertSuccessful(result: Long): Boolean{
    return result != -1L
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: CharSequence) = requireContext().toast(message)

fun Fragment.loadAnimation(view: View, animId: Int) {
    view.startAnimation(AnimationUtils.loadAnimation(requireContext(), animId))
}

fun Fragment.loadAnimation(vararg views: View, animId: Int) {
    for (view in views) {
        view.startAnimation(AnimationUtils.loadAnimation(requireContext(), animId))
    }
}

/**
 * This method will return size in SDP & SSP which ever is requested in dimeSize param
 * @param dimenSize: Int
 */
fun Fragment.getSizeInSDP(dimenSize: Int): Int {
    return requireContext().resources.getDimensionPixelSize(dimenSize)
}