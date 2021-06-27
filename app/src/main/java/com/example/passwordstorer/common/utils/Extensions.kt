package com.example.passwordstorer.common.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.passwordstorer.BuildConfig
import com.example.passwordstorer.common.customviews.PinEntryView

fun NavController.safeNavigate(direction: NavDirections) {
    "eLog".eLog("Click happened")
    currentDestination?.getAction(direction.actionId)?.run {
        "eLog".eLog( "Click Propagated")
        navigate(direction)
    }
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun EditText.text(): String {
    return if (text.isNotEmpty())
        text.toString() else ""
}

fun PinEntryView.text(): String {
    return if (text.isNotEmpty())
        text.toString() else ""
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Fragment.toast(message: CharSequence) = requireContext().toast(message)

fun String.eLog(msg: String) {
    if (BuildConfig.DEBUG)
        Log.e(this, msg)
}

fun String.vLog(msg: String) {
    if (BuildConfig.DEBUG)
        Log.v(this, msg)
}

fun String.dLog(msg: String) {
    if (BuildConfig.DEBUG)
        Log.d(this, msg)
}

fun String.iLog(msg: String) {
    if (BuildConfig.DEBUG)
        Log.i(this, msg)
}

fun String.wLog(msg: String) {
    if (BuildConfig.DEBUG)
        Log.w(this, msg)
}
