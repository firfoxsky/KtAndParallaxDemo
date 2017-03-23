package com.example.pengtaoh.ktproject.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

import com.example.pengtaoh.ktproject.fragment.outlayer.WelcomAnimFragment
import com.example.pengtaoh.ktproject.utils.DisplayUtil


/**
 * 顶层ViewPager,包含2个fragment childview
 * 处于第一个fragment,所有事件将被拦截在本层View,手动分发到指定子View
 * 处于第二个fragment,释放拦截
 * @author Zhongdaxia
 */

class ParentViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

    /**
     * 跳过按钮相关
     */
    private var mSkipFlag: Boolean? = true
    private var mCx: Float = 0.toFloat()
    private var mCy: Float = 0.toFloat()
    private var mTvSkipLocation: IntArray? = null
    private var margin: Int = 0
    private var left: Int? = 0
    private var top: Int? = 0
    private var right: Int? = 0
    private var bottom: Int? = 0

    private var mWelcomAnimFragment: WelcomAnimFragment? = null

    fun setWelcomAnimFragment(welcomAnimFragment: WelcomAnimFragment) {
        this.mWelcomAnimFragment = welcomAnimFragment
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (mLoginPageLock) {
            requestDisallowInterceptTouchEvent(true)
            return super.onInterceptTouchEvent(ev)
        }
        return true
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (this.currentItem == 1 || mLoginPageLock) {
            return true
        }
        if (mWelcomAnimFragment != null) {
            mCx = ev.x
            mCy = ev.y

            /**
             * 由于子View被拦截,这里通过计算跳过按钮的坐标手动处理跳过click事件
             */
            if (mTvSkipLocation == null) {
                mTvSkipLocation = IntArray(2)
                mWelcomAnimFragment!!.tv_skip!!.getLocationOnScreen(mTvSkipLocation)
            }
            if (left == 0) {
                margin = DisplayUtil.dip2px(context, 10f)
                left = mTvSkipLocation!![0] - margin
                top = mTvSkipLocation!![1] - margin
                right = mTvSkipLocation!![0] + mWelcomAnimFragment!!.tv_skip!!.width + margin
                bottom = mTvSkipLocation!![1] + mWelcomAnimFragment!!.tv_skip!!.height + margin
            }
            if (mCx - left!!.toFloat() > 0 && right!!.toFloat() - mCx > 0 && mCy - top!!.toFloat() > 0 && bottom!!.toFloat() - mCy > 0 && !mLoginPageLock) {
            } else {
                mSkipFlag = false
            }
            if (ev.action == MotionEvent.ACTION_UP) {
                if (mCx - left!!.toFloat() > 0 && right!!.toFloat() - mCx > 0 && mCy!!.toFloat() - top!!.toFloat() > 0 && bottom!!.toFloat() - mCy > 0 && !mLoginPageLock && mSkipFlag!!) {
                    if (mWelcomAnimFragment!!.mWelcomAnimFragmentInterface != null) {
                        mWelcomAnimFragment!!.mWelcomAnimFragmentInterface!!.onSkip()
                    }
                }
                mSkipFlag = true
                mCx = 0f
                mCy = 0f
            }

            /**
             * touch事件由顶层viewpager捕捉,手动分发到两个子viewpager
             */
            if (!mWelcomAnimFragment!!.imageViewPager!!.mIsLockScoll) {
                mWelcomAnimFragment!!.imageViewPager!!.onTouchEvent(ev)
            }
            mWelcomAnimFragment!!.textViewPager!!.onTouchEvent(ev)
            if (mWelcomAnimFragment!!.mIsMoveParent) {
                return super.onTouchEvent(ev)
            }
        }
        return true
    }

    companion object {

        public var mLoginPageLock = false
    }
}
