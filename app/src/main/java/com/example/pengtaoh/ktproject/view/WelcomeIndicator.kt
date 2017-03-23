package com.example.pengtaoh.ktproject.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.pengtaoh.ktproject.R
import com.example.pengtaoh.ktproject.utils.DisplayUtil
import com.nineoldandroids.animation.Animator
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import java.util.*


/**
 * 滑动指示器
 * @author Zhongdaxia
 */

class WelcomeIndicator(internal var mContext: Context, attrs: AttributeSet) : LinearLayout(mContext, attrs) {
    internal var mImageViews: ArrayList<ImageView>? = null

    internal var heightSelect: Int = 0
    internal var bmpSelect: Bitmap
    internal var bmpNomal: Bitmap

    internal var mOutAnimatorSet: AnimatorSet? = null
    internal var mInAnimatorSet: AnimatorSet? = null

    init {
        this.orientation = LinearLayout.HORIZONTAL

        heightSelect = DisplayUtil.dip2px(mContext, 24f)
        bmpSelect = BitmapFactory.decodeResource(resources, R.mipmap.welcome_indicator_point_select)
        bmpNomal = BitmapFactory.decodeResource(resources, R.mipmap.welcome_indicator_point_nomal)
    }

    fun init(count: Int) {
        mImageViews = ArrayList<ImageView>()
        for (i in 0..count - 1) {
            val rl = RelativeLayout(mContext)
            val params = LinearLayout.LayoutParams(heightSelect, heightSelect)
            val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
            val imageView = ImageView(mContext)

            if (i == 0) {
                imageView.setImageBitmap(bmpSelect)
                rl.addView(imageView, layoutParams)
            } else {
                imageView.setImageBitmap(bmpNomal)
                rl.addView(imageView, layoutParams)
            }
            this.addView(rl, params)
            mImageViews!!.add(imageView)
        }
    }

    fun play(startPosition: Int, nextPosition: Int) {
        if (startPosition < 0 || nextPosition < 0 || nextPosition == startPosition) {
            return
        }

        val imageViewStrat = mImageViews!![startPosition]
        val imageViewNext = mImageViews!![nextPosition]

        val anim1 = ObjectAnimator.ofFloat(imageViewStrat, "scaleX", 1.0f, 0.25f)
        val anim2 = ObjectAnimator.ofFloat(imageViewStrat, "scaleY", 1.0f, 0.25f)

        if (mOutAnimatorSet != null && mOutAnimatorSet!!.isRunning()) {
            mOutAnimatorSet!!.cancel()
            mOutAnimatorSet = null
        }
        mOutAnimatorSet = AnimatorSet()
        mOutAnimatorSet!!.play(anim1).with(anim2)
        mOutAnimatorSet!!.setDuration(100)

        val animIn1 = ObjectAnimator.ofFloat(imageViewNext, "scaleX", 0.25f, 1.0f)
        val animIn2 = ObjectAnimator.ofFloat(imageViewNext, "scaleY", 0.25f, 1.0f)

        if (mInAnimatorSet != null && mInAnimatorSet!!.isRunning()) {
            mInAnimatorSet!!.cancel()
            mInAnimatorSet = null
        }
        mInAnimatorSet = AnimatorSet()
        mInAnimatorSet!!.play(animIn1).with(animIn2)
        mInAnimatorSet!!.setDuration(100)

        anim1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                imageViewStrat.setImageBitmap(bmpNomal)
                val animFill1 = ObjectAnimator.ofFloat(imageViewStrat, "scaleX", 1.0f)
                val animFill2 = ObjectAnimator.ofFloat(imageViewStrat, "scaleY", 1.0f)
                val mFillAnimatorSet = AnimatorSet()
                mFillAnimatorSet.play(animFill1).with(animFill2)
                mFillAnimatorSet.start()
                imageViewNext.setImageBitmap(bmpSelect)
                mInAnimatorSet!!.start()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        })
        mOutAnimatorSet!!.start()
    }
}
