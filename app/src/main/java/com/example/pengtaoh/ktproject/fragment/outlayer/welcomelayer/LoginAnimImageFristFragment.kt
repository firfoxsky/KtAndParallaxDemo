package com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.pengtaoh.ktproject.R
import com.example.pengtaoh.ktproject.fragment.base.LoginAnimImageBaseFragment
import com.example.pengtaoh.ktproject.utils.DisplayUtil
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator

import com.nineoldandroids.view.ViewHelper


/**
 * 第一页动画
 */
class LoginAnimImageFristFragment : LoginAnimImageBaseFragment() {

    private val mScrollToBottomBitmapHeight = 864
    private val mScrollBitmapNomalHeight = 1286
    private val mScrollBitmapSpitHeight = 150
    internal var mMarginTopHeigth: Int = 0
    private var iv_scroll: ImageView? = null
    private var tv_bg: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater?, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_welcomanim_image_frist, null) as ViewGroup
        iv_scroll = view.findViewById(R.id.iv_scroll) as ImageView
        tv_bg = view.findViewById(R.id.tv_bg) as ImageView

        var bmpShield = BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_phone)
        var bmpScroll = BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_frist_scrollbg)
        mScrollBitmapHeight = mScrollBitmapNomalHeight

        iv_scroll!!.scaleType = ImageView.ScaleType.MATRIX
        iv_scroll!!.setImageBitmap(bitmapScale(mImageViewWidth, bmpScroll, BITMAP_SCROLL))

        tv_bg!!.scaleType = ImageView.ScaleType.MATRIX
        tv_bg!!.setImageBitmap(bitmapScale(mImageViewWidth, bmpShield, BITMAP_SHIELD))

        scrollMarginTopHeigth = mNewScrollBitmapHeight * mScrollToBottomBitmapHeight / mScrollBitmapHeight

        var paramsGoods = iv_scroll!!.layoutParams as RelativeLayout.LayoutParams
        mMarginTopHeigth = mNewScrollBitmapHeight * mScrollBitmapSpitHeight / mScrollBitmapNomalHeight - DisplayUtil.dip2px(activity, 20f)
        paramsGoods.topMargin = mMarginTopHeigth
        iv_scroll!!.layoutParams = paramsGoods
        return view
    }

    internal var scrollMarginTopHeigth: Int = 0
    override fun playInAnim() {
        if (mAnimStartY < 0) {
            mAnimStartY = ViewHelper.getY(iv_scroll).toInt()
        }

        if (mObjectAnimator == null) {
            mObjectAnimator = ObjectAnimator.ofFloat(iv_scroll, "y", mMarginTopHeigth.toFloat(), -scrollMarginTopHeigth.toFloat() + mMarginTopHeigth.toFloat())
        }
        if (mAnimatorSet == null) {
            mAnimatorSet = AnimatorSet()
        }

        if (mAnimatorSet!!.isRunning) {
            mAnimatorSet!!.cancel()
        }

        mAnimatorSet!!.play(mObjectAnimator)
        mAnimatorSet!!.duration = 3000
        mAnimatorSet!!.start()
    }

    override fun playOutAnim() {}

    override fun reset() {
        if (mAnimatorSet != null) {
            mAnimatorSet!!.cancel()
        }
    }
}
