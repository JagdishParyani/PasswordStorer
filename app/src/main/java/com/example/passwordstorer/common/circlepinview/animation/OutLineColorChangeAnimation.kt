package com.example.passwordstorer.common.circlepinview.animation

import com.example.passwordstorer.common.circlepinview.CircleView

internal class OutLineColorChangeAnimation(
    private val circleView: CircleView
) : ColorChangeAnimation(circleView) {

    override fun getColor(): Int = circleView.getOutLineColor()

    override fun setColor(color: Int) {
        circleView.setOutLineColor(color)
    }
}