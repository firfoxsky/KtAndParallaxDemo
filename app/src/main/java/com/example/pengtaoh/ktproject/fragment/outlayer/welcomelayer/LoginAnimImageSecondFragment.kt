package com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.pengtaoh.ktproject.R
import com.example.pengtaoh.ktproject.fragment.base.LoginAnimImageBaseFragment
import com.example.pengtaoh.ktproject.utils.DisplayUtil

import com.nineoldandroids.animation.Animator
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import com.nineoldandroids.view.ViewHelper


/**
 * 第二页动画
 */
class LoginAnimImageSecondFragment : LoginAnimImageBaseFragment() {

    private val DURATION_GOODS = 1000

    private val mScrollBitmapGoodsY = 442
    private val mScrollBitmapGoodsX = 30
    private val mScrollBitmapSpitHeight = 156
    private val mScrollBitmapNomalHeight = 1056
    private val mGoodsBitmapNomalHeight = 198

    private var iv_scroll: ImageView? = null
    private var iv_shield: ImageView? = null
    private var iv_goods: ImageView? = null
    private var iv_buy: ImageView? = null

    internal var bmpBuysIng: Bitmap? = null
    internal var bmpBuysed: Bitmap? = null

    internal var animatorSetGoods: android.view.animation.AnimationSet? = null
    internal var scaleAnimation: Animation? = null
    internal var rotateAnimation: Animation? = null
    internal var translateAnimation: Animation? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_welcomanim_image_second, null) as ViewGroup
        iv_scroll = view.findViewById(R.id.iv_scroll) as ImageView
        iv_shield = view.findViewById(R.id.iv_shield) as ImageView
        iv_buy = view.findViewById(R.id.iv_buy) as ImageView
        iv_goods = view.findViewById(R.id.iv_goods) as ImageView

        val bmpScroll = BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_second_scrollbg)
        val bmpShield = BitmapFactory.decodeResource(resources, R.mipmap.welcome_shield)
        val bmpGoods = BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_second_goods)

        mScrollBitmapHeight = mScrollBitmapNomalHeight

        iv_shield!!.scaleType = ImageView.ScaleType.MATRIX
        iv_shield!!.setImageBitmap(bitmapScale(mImageViewWidth, bmpShield, BITMAP_SHIELD))
        iv_shield!!.visibility = View.GONE

        iv_scroll!!.scaleType = ImageView.ScaleType.MATRIX
        val bitmap = bitmapScale(mImageViewWidth, bmpScroll, BITMAP_SCROLL)
        iv_scroll!!.setImageBitmap(bitmap)

        iv_goods!!.setImageBitmap(bitmapScale(bitmap.height * mGoodsBitmapNomalHeight / mScrollBitmapNomalHeight, bmpGoods, BITMAP_SHIELD))
        iv_goods!!.visibility = View.GONE

        bmpBuysIng = bitmapScale(BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_second_buging))
        bmpBuysed = bitmapScale(BitmapFactory.decodeResource(resources, R.mipmap.welcomeanim_second_bugged))

        val params = iv_buy!!.layoutParams as RelativeLayout.LayoutParams
        val ivBuyMargin = DisplayUtil.dip2px(activity, 50f)
        params.rightMargin = ivBuyMargin
        iv_buy!!.setImageBitmap(bmpBuysed)
        iv_buy!!.layoutParams = params

        val paramsGoods = iv_goods!!.layoutParams as RelativeLayout.LayoutParams
        val goodsMarginTopHeigth = mNewScrollBitmapHeight * mScrollBitmapGoodsY / mScrollBitmapNomalHeight
        val goodsMarginLeftHeigth = mNewScrollBitmapHeight * mScrollBitmapGoodsX / mScrollBitmapNomalHeight
        paramsGoods.topMargin = goodsMarginTopHeigth
        paramsGoods.leftMargin = goodsMarginLeftHeigth
        iv_goods!!.layoutParams = paramsGoods
        return view
    }

    override fun playInAnim() {
        if (mAnimStartY < 0) {
            mAnimStartY = ViewHelper.getY(iv_scroll).toInt()
        }
        ViewHelper.setY(iv_scroll, mAnimStartY.toFloat())

        if (mObjectAnimator == null) {
            var animEndY = mAnimStartY - mNewScrollBitmapHeight * mScrollBitmapSpitHeight / mScrollBitmapHeight
            mObjectAnimator = ObjectAnimator.ofFloat(iv_scroll, "y", mAnimStartY.toFloat(), animEndY.toFloat())

            mObjectAnimator!!.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    iv_shield!!.visibility = View.VISIBLE
                    iv_buy!!.visibility = View.VISIBLE
                    iv_goods!!.visibility = View.VISIBLE
                    iv_buy!!.setImageBitmap(bmpBuysIng)

                    iv_shield!!.postDelayed({
                        iv_shield!!.visibility = View.VISIBLE
                        iv_buy!!.visibility = View.VISIBLE
                        iv_goods!!.visibility = View.VISIBLE

                        val goodsX = ViewHelper.getX(iv_goods)
                        val goodsY = ViewHelper.getY(iv_goods)
                        val buyX = ViewHelper.getX(iv_buy)
                        val buyY = ViewHelper.getY(iv_buy)

                        val scale = iv_buy!!.height.toFloat() / iv_goods!!.height

                        if (scaleAnimation == null) {
                            scaleAnimation = ScaleAnimation(1.0f, scale, 1.0f, scale)
                        }

                        if (rotateAnimation == null) {
                            rotateAnimation = RotateAnimation(0f, 180f,
                                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                        }

                        if (translateAnimation == null) {
                            translateAnimation = TranslateAnimation(
                                    Animation.ABSOLUTE, 0f,
                                    Animation.ABSOLUTE, buyX - goodsX,
                                    Animation.ABSOLUTE, 0f,
                                    Animation.ABSOLUTE, buyY - goodsY)
                        }

                        if (animatorSetGoods == null) {
                            animatorSetGoods = android.view.animation.AnimationSet(true)
                            animatorSetGoods!!.duration = DURATION_GOODS.toLong()
                            animatorSetGoods!!.addAnimation(rotateAnimation)
                            animatorSetGoods!!.addAnimation(scaleAnimation)
                            animatorSetGoods!!.addAnimation(translateAnimation)
                            animatorSetGoods!!.setAnimationListener(object : Animation.AnimationListener {
                                override fun onAnimationStart(animation: Animation) {
                                    mIsAnimatorSetGoodsStart = true
                                }

                                override fun onAnimationEnd(animation: Animation) {
                                    iv_buy!!.postDelayed({ iv_buy!!.setImageBitmap(bmpBuysed) }, 200)
                                    mIsAnimatorSetGoodsStart = false
                                }

                                override fun onAnimationRepeat(animation: Animation) {}

                            })
                        } else {
                            animatorSetGoods!!.cancel()
                        }
                        iv_buy!!.visibility = View.VISIBLE
                        iv_goods!!.visibility = View.VISIBLE
                        iv_goods!!.startAnimation(animatorSetGoods)
                    }, 400)
                }

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}
            })
        }

        if (mAnimatorSet == null) {
            mAnimatorSet = AnimatorSet()
        }
        if (mAnimatorSet!!.isRunning) {
            mAnimatorSet!!.cancel()
        }

        iv_shield!!.visibility = View.GONE
        iv_goods!!.visibility = View.GONE
        iv_buy!!.visibility = View.GONE
        iv_goods!!.clearAnimation()
        iv_buy!!.setImageBitmap(bmpBuysIng)
        mAnimatorSet!!.play(mObjectAnimator)
        mAnimatorSet!!.duration = 1000
        mAnimatorSet!!.start()
    }

    override fun playOutAnim() {}

    internal var mIsAnimatorSetGoodsStart = false

    override fun reset() {
        if (mAnimatorSet != null && mAnimatorSet!!.isRunning) {
            mAnimatorSet!!.cancel()
        }
        if (animatorSetGoods != null) {
            animatorSetGoods!!.cancel()
            iv_goods!!.clearAnimation()
        }

        iv_shield!!.visibility = View.GONE
        iv_goods!!.visibility = View.GONE
        iv_buy!!.visibility = View.GONE
        iv_buy!!.setImageBitmap(bmpBuysIng)
    }
}
