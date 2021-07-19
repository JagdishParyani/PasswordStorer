package com.example.passwordstorer.common.customviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.forEach
import androidx.viewpager.widget.ViewPager
import com.example.passwordstorer.common.utils.eLog
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView


class CustomBottomNav : BottomNavigationViewInner {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setIconVisibility(visibility: Boolean): BottomNavigationViewInner? {
        return try {
            super.setIconVisibility(visibility)
        } catch (e: Exception) {
            this
        }
    }

    override fun setTextVisibility(visibility: Boolean): BottomNavigationViewInner? {
        return try {
            super.setTextVisibility(visibility)
        } catch (e: Exception) {
            this
        }
    }

    fun disableTooltipText() {
        try {
            menu.forEach {
                val menuView = findViewById<View>(it.itemId)
                menuView.setOnLongClickListener {
                    true
                }
            }
        } catch (e: Exception) {
            "Exception".eLog(e.toString())
        }
    }

    override fun enableAnimation(enable: Boolean): BottomNavigationViewInner? {
        return try {
            super.enableAnimation(enable)
        } catch (e: Exception) {
            this
        }
    }

    override fun enableShiftingMode(enable: Boolean): BottomNavigationViewInner? {
        return try {
            super.enableShiftingMode(enable)
        } catch (e: Exception) {
            this
        }
    }

    override fun enableItemShiftingMode(enable: Boolean): BottomNavigationViewInner? {
        return try {
            super.enableItemShiftingMode(enable)
        } catch (e: Exception) {
            this
        }
    }

    override fun getCurrentItem(): Int {
        return try {
            super.getCurrentItem()
        } catch (e: Exception) {
            0
        }
    }

    override fun getMenuItemPosition(item: MenuItem?): Int {
        return try {
            super.getMenuItemPosition(item)
        } catch (e: Exception) {
            0
        }
    }

    override fun setCurrentItem(index: Int): BottomNavigationViewInner? {
        return try {
            super.setCurrentItem(index)
        } catch (e: Exception) {
            this
        }
    }

    override fun getOnNavigationItemSelectedListener(): OnNavigationItemSelectedListener? {
        return try {
            super.getOnNavigationItemSelectedListener()
        } catch (e: Exception) {
            null
        }
    }

    override fun setOnNavigationItemSelectedListener(listener: OnNavigationItemSelectedListener?) {
        try {
            super.setOnNavigationItemSelectedListener(listener)
        } catch (e: Exception) {
        }
    }

    override fun getBottomNavigationMenuView(): BottomNavigationMenuView? {
        return super.getBottomNavigationMenuView()
    }

    override fun clearIconTintColor(): BottomNavigationViewInner? {
        return try {
            super.clearIconTintColor()
        } catch (e: Exception) {
            this
        }
    }

    override fun getBottomNavigationItemViews(): Array<BottomNavigationItemView?>? {
        return try {
            super.getBottomNavigationItemViews()
        } catch (e: Exception) {
            null
        }
    }

    override fun getBottomNavigationItemView(position: Int): BottomNavigationItemView? {
        return try {
            super.getBottomNavigationItemView(position)
        } catch (e: Exception) {
            null
        }
    }

    override fun getIconAt(position: Int): ImageView? {
        return try {
            super.getIconAt(position)
        } catch (e: Exception) {
            null
        }
    }

    override fun getSmallLabelAt(position: Int): TextView? {
        return try {
            super.getSmallLabelAt(position)
        } catch (e: Exception) {
            null
        }
    }

    override fun getLargeLabelAt(position: Int): TextView? {
        return try {
            super.getLargeLabelAt(position)
        } catch (e: Exception) {
            null
        }
    }

    override fun getItemCount(): Int {
        return try {
            super.getItemCount()
        } catch (e: Exception) {
            0
        }
    }

    override fun setSmallTextSize(sp: Float): BottomNavigationViewInner? {
        return try {
            super.setSmallTextSize(sp)
        } catch (e: Exception) {
            this
        }
    }

    override fun setLargeTextSize(sp: Float): BottomNavigationViewInner? {
        return try {
            super.setLargeTextSize(sp)
        } catch (e: Exception) {
            this
        }
    }

    override fun setTextSize(sp: Float): BottomNavigationViewInner? {
        return try {
            super.setTextSize(sp)
        } catch (e: Exception) {
            this
        }
    }

    override fun setIconSizeAt(
        position: Int,
        width: Float,
        height: Float
    ): BottomNavigationViewInner? {
        return try {
            super.setIconSizeAt(position, width, height)
        } catch (e: Exception) {
            this
        }
    }

    override fun setIconSize(width: Float, height: Float): BottomNavigationViewInner? {
        return try {
            super.setIconSize(width, height)
        } catch (e: Exception) {
            this
        }
    }

    override fun setIconSize(dpSize: Float): BottomNavigationViewInner? {
        return try {
            super.setIconSize(dpSize)
        } catch (e: Exception) {
            this
        }
    }

    override fun setItemHeight(height: Int): BottomNavigationViewInner? {
        return try {
            super.setItemHeight(height)
        } catch (e: Exception) {
            this
        }
    }

    override fun getItemHeight(): Int {
        return try {
            super.getItemHeight()
        } catch (e: Exception) {
            0
        }
    }

    override fun setTypeface(typeface: Typeface?, style: Int): BottomNavigationViewInner? {
        return try {
            super.setTypeface(typeface, style)
        } catch (e: Exception) {
            this
        }
    }

    override fun setTypeface(typeface: Typeface?): BottomNavigationViewInner? {
        return try {
            super.setTypeface(typeface)
        } catch (e: Exception) {
            this
        }
    }

    override fun setupWithViewPager(viewPager: ViewPager?): BottomNavigationViewInner? {
        return try {
            super.setupWithViewPager(viewPager)
        } catch (e: Exception) {
            this
        }
    }

    override fun setupWithViewPager(
        viewPager: ViewPager?,
        smoothScroll: Boolean
    ): BottomNavigationViewInner? {
        return try {
            super.setupWithViewPager(viewPager, smoothScroll)
        } catch (e: Exception) {
            this
        }
    }

    override fun enableShiftingMode(position: Int, enable: Boolean): BottomNavigationViewInner? {
        return try {
            super.enableShiftingMode(position, enable)
        } catch (e: Exception) {
            this
        }
    }

    override fun setItemBackground(position: Int, background: Int): BottomNavigationViewInner? {
        return try {
            super.setItemBackground(position, background)
        } catch (e: Exception) {
            this
        }
    }

    override fun setIconTintList(position: Int, tint: ColorStateList?): BottomNavigationViewInner? {
        return try {
            super.setIconTintList(position, tint)
        } catch (e: Exception) {
            this
        }
    }

    override fun setTextTintList(position: Int, tint: ColorStateList?): BottomNavigationViewInner? {
        return try {
            super.setTextTintList(position, tint)
        } catch (e: Exception) {
            this
        }
    }

    override fun setIconsMarginTop(marginTop: Int): BottomNavigationViewInner? {
        return try {
            super.setIconsMarginTop(marginTop)
        } catch (e: Exception) {
            this
        }
    }

    override fun setIconMarginTop(position: Int, marginTop: Int): BottomNavigationViewInner? {
        return try {
            super.setIconMarginTop(position, marginTop)
        } catch (e: Exception) {
            this
        }
    }
}