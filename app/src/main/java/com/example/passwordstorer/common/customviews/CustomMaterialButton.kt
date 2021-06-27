package com.example.passwordstorer.common.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.example.passwordstorer.R
import com.example.passwordstorer.common.utils.eLog
import com.google.android.material.button.MaterialButton

class CustomMaterialButton : MaterialButton {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        setCustomStyle(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        setCustomStyle(context, attrs)
    }

    private fun setCustomStyle(context: Context, attrs: AttributeSet?) {
        val customView = context.obtainStyledAttributes(attrs, R.styleable.CustomMaterialButton)
        val customFont = customView.getString(R.styleable.CustomMaterialButton_customButtonFont)
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