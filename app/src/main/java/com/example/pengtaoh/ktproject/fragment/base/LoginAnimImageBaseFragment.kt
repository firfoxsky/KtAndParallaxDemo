package com.example.pengtaoh.ktproject.fragment.base

import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.support.v4.app.Fragment

import com.example.pengtaoh.ktproject.utils.DisplayUtil
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator


/**
 * 上层图片滚动fragment基类
 */
abstract class LoginAnimImageBaseFragment : Fragment() {

    val BITMAP_SCROLL = 11
    val BITMAP_SHIELD = 12

    var mAnimStartY = -1
    var mScrollBitmapHeight = 0
    var mNewScrollBitmapHeight = 0
    var mImageViewWidth = 0

    var mAnimatorSet: AnimatorSet? = null
    var mObjectAnimator: ObjectAnimator? = null

    internal var mScaleWidth = 0f
    internal var mScaleHeight = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parentViewWidth = DisplayUtil.getDisplayWidthPixels(activity) - DisplayUtil.dip2px(activity, 40f)
        mImageViewWidth = (parentViewWidth - DisplayUtil.dip2px(activity, 60f))
    }

    fun bitmapScale(ivWidth: Int, bitmap: Bitmap, type: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val newWidth = ivWidth
        val newHeight = height * newWidth / width

        if (type == BITMAP_SCROLL) {
            mNewScrollBitmapHeight = newHeight
        }

        mScaleWidth = newWidth.toFloat() / width
        mScaleHeight = newHeight.toFloat() / height

        var matrix = Matrix()
        matrix.postScale(mScaleWidth, mScaleHeight)
        var resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        return resizedBitmap
    }

    fun bitmapScale(bitmap: Bitmap): Bitmap {
        var width = bitmap.width
        var height = bitmap.height
        var matrix = Matrix()
        matrix.postScale(mScaleWidth, mScaleHeight)
        var resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true)
        return resizedBitmap
    }

    open fun playInAnim() {}
    open fun playOutAnim() {}
    open fun reset() {}
}
