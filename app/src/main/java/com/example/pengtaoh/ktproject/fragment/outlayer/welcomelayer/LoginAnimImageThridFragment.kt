package com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.pengtaoh.ktproject.R
import com.example.pengtaoh.ktproject.fragment.base.LoginAnimImageBaseFragment
import com.nineoldandroids.animation.Animator
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import com.nineoldandroids.view.ViewHelper


/**
 * 第三页动画
 */
class LoginAnimImageThridFragment : LoginAnimImageBaseFragment() {

    private val mScrollBitmapSpitHeight = 350
    private val mScrollBitmapNomalHeight = 1378
    private val mCareToTopHeight = 288
    private val mCareToLeftHeight = -10

    private var iv_scroll: ImageView? = null
    private var iv_shield: ImageView? = null
    private var iv_care: ImageView? = null

    internal var mCareObjectAnimator: ObjectAnimator? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_welcomanim_image_thrid, null) as ViewGroup
        iv_scroll = view.findViewById(R.id.iv_scroll) as ImageView
        iv_shield = view.findViewById(R.id.iv_shield) as ImageView
        iv_care = view.findViewById(R.id.iv_care) as ImageView

        val bmpScroll = BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_thrid_scrollbg)
        val bmpShield = BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_thrid_shield)
        mScrollBitmapHeight = mScrollBitmapNomalHeight

        iv_shield!!.scaleType = ImageView.ScaleType.MATRIX
        iv_shield!!.setImageBitmap(bitmapScale(mImageViewWidth.toInt(), bmpShield, BITMAP_SHIELD))
        iv_shield!!.visibility = View.GONE

        iv_scroll!!.scaleType = ImageView.ScaleType.MATRIX
        iv_scroll!!.setImageBitmap(bitmapScale(mImageViewWidth.toInt(), bmpScroll, BITMAP_SCROLL))


        val bmpCare = bitmapScale(BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_thrid_shield_care))
        iv_care!!.scaleType = ImageView.ScaleType.MATRIX
        iv_care!!.setImageBitmap(bmpCare)

        val paramsGoods = iv_care!!.layoutParams as RelativeLayout.LayoutParams
        val goodsMarginTopHeigth = mNewScrollBitmapHeight * mCareToTopHeight / mScrollBitmapNomalHeight
        val goodsMarginLeft = mNewScrollBitmapHeight * mCareToLeftHeight / mScrollBitmapNomalHeight
        paramsGoods.topMargin = goodsMarginTopHeigth
        paramsGoods.leftMargin = goodsMarginLeft
        iv_care!!.layoutParams = paramsGoods
        iv_care!!.visibility = View.GONE
        return view
    }

    override fun playInAnim() {
        if (mAnimStartY < 0) {
            mAnimStartY = ViewHelper.getY(iv_scroll!!).toInt()
        }
        ViewHelper.setY(iv_scroll!!, mAnimStartY.toFloat())

        if (mObjectAnimator == null) {
            var animSecond = mAnimStartY - mNewScrollBitmapHeight * mScrollBitmapSpitHeight/ mScrollBitmapHeight
            mObjectAnimator = ObjectAnimator.ofFloat(iv_scroll, "y", mAnimStartY.toFloat(), animSecond.toFloat())
            mObjectAnimator!!.addListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    iv_shield!!.visibility = View.VISIBLE
                    iv_care!!.visibility = View.VISIBLE
                    if (mCareObjectAnimator == null) {
                        mCareObjectAnimator = ObjectAnimator.ofInt(iv_care, "alpha", 0, 255)
                        mCareObjectAnimator!!.interpolator = AccelerateDecelerateInterpolator()
                        mCareObjectAnimator!!.duration = 500
                    }
                    if (mCareObjectAnimator!!.isRunning) {
                        mCareObjectAnimator!!.cancel()
                    }
                    mCareObjectAnimator!!.start()
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
        if(mAnimatorSet ==null){
            mAnimatorSet = AnimatorSet()
        }
        if (mAnimatorSet!!.isRunning) {
            mAnimatorSet!!.cancel()
        }

        iv_shield!!.visibility = View.GONE
        iv_care!!.visibility = View.GONE
        mAnimatorSet!!.play(mObjectAnimator)
        mAnimatorSet!!.duration = 1000
        mAnimatorSet!!.start()
    }

    override fun playOutAnim() {

    }

    override fun reset() {
        if (mAnimatorSet != null) {
            mAnimatorSet!!.cancel()
        }
        iv_care!!.visibility = View.GONE
        iv_shield!!.visibility = View.GONE
    }
}
