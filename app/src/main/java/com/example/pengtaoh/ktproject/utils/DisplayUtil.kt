package com.example.pengtaoh.ktproject.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

object DisplayUtil {

    /** 屏幕宽度    */
    private var DisplayWidthPixels = 0
    /** 屏幕高度    */
    private var DisplayheightPixels = 0

    /**
     * 获取屏幕参数
     * @param context
     */
    private fun getDisplayMetrics(context: Context) {
        val dm = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(dm)
        // 宽度
        DisplayWidthPixels = dm.widthPixels
        // 高度
        DisplayheightPixels = dm.heightPixels
    }

    /**
     * 获取屏幕宽度
     * @param context
     * *
     * @return
     */
    fun getDisplayWidthPixels(context: Context?): Int {
        if (context == null) {
            return -1
        }
        if (DisplayWidthPixels == 0) {
            getDisplayMetrics(context)
        }
        return DisplayWidthPixels
    }

    /**
     * 获取屏幕高度
     * @param context
     * *
     * @return
     */
    fun getDisplayheightPixels(context: Context?): Int {
        if (context == null) {
            return -1
        }
        if (DisplayheightPixels == 0) {
            getDisplayMetrics(context)
        }
        return DisplayheightPixels
    }

    /**
     * 将px值转换为dip或dp值

     * @param pxValue
     * *
     * @return
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值

     * @param dipValue
     * *
     * @return
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值

     * @param pxValue
     * *
     * @return
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值
     * @param spValue
     * *
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }
}
