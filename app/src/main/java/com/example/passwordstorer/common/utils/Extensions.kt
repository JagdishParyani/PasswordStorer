package com.example.passwordstorer.common.utils

import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.passwordstorer.BuildConfig
import com.example.passwordstorer.common.customviews.PinEntryView

fun NavController.safeNavigate(direction: NavDirections) {
    "eLog".eLog("Click happened")
    currentDestination?.getAction(direction.actionId)?.run {
        "eLog".eLog("Click Propagated")
        navigate(direction)
    }
}

fun View.getCustomTypeFace(customFont: String): Typeface {
    var customTypeface: Typeface = if (this is TextView) {
        this.typeface
    } else {
        Typeface.DEFAULT
    }
    try {
        customTypeface = Typeface.createFromAsset(context.assets, "fonts/$customFont")
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return customTypeface
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
    return text.toString()
}

fun EditText.isTextBlank(): Boolean {
    return this.text.isBlank() && text.isEmpty()
}

fun PinEntryView.isTextBlank(): Boolean {
    return this.text.isBlank() && text.isEmpty()
}

fun PinEntryView.text(): String {
    return text.toString()
}

fun String.eLog(msg: String) {
    log("e", msg)
}

fun String.vLog(msg: String) {
    log("v", msg)
}

fun String.dLog(msg: String) {
    log("d", msg)
}

fun String.iLog(msg: String) {
    log("i", msg)
}

fun String.wLog(msg: String) {
    log("w", msg)
}

fun String.log(logType: String, msg: String) {
    if (BuildConfig.DEBUG) {
        when (logType) {
            "e" -> Log.e(this, "-->>> $msg")
            "v" -> Log.v(this, "-->>> $msg")
            "d" -> Log.d(this, "-->>> $msg")
            "i" -> Log.i(this, "-->>> $msg")
            "w" -> Log.w(this, "-->>> $msg")
        }
    }
}