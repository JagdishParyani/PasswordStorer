package com.example.passwordstorer.common.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.eLog

/**
 * This is Custom TextView Class extended from TextView Class of Android
 * Using @property setCustomFont we can set Custom Fonts to text of TextView
 * This class can also be used for showing custom text from ttf files as icons.
 */
class CustomTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setCustomStyle(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setCustomStyle(context, attrs)
    }

    private fun setCustomStyle(context: Context, attrs: AttributeSet?) {
        val customView = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
        val customFont = customView.getString(R.styleable.CustomTextView_customFont)
        initCustomFont(context, customFont)
        customView.recycle()
    }

    private fun initCustomFont(context: Context, customFont: String?) {
        var customTypeface: Typeface = Typeface.DEFAULT
        try {
            customTypeface = Typeface.createFromAsset(context.assets, "fonts/$customFont")
        } catch (e: Exception) {
            TAG.eLog("Could not get typeface: " + e.message)
            e.printStackTrace()
        }
        typeface = customTypeface
    }

    companion object {
        private val TAG: String = this::class.simpleName.toString()
    }
}