package com.example.passwordstorer.common.circlepinview.animation

import com.example.passwordstorer.common.circlepinview.CircleView

internal class FillAndStrokeColorChangeAnimation(
    private val circleView: CircleView
) : ColorChangeAnimation(circleView) {

    override fun getColor(): Int = circleView.getFillAndStrokeCircleColor()

    override fun setColor(color: Int) {
        circleView.setFillAndStrokeCircleColor(color)
    }
}