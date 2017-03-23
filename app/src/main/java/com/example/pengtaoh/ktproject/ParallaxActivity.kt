package com.example.pengtaoh.ktproject

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.ImageView
import android.widget.Toast
import com.example.pengtaoh.ktproject.fragment.outlayer.LoginAnimFragment
import com.example.pengtaoh.ktproject.fragment.outlayer.WelcomAnimFragment
import com.example.pengtaoh.ktproject.utils.DisplayUtil
import com.example.pengtaoh.ktproject.view.ParentViewPager
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import com.nineoldandroids.view.ViewHelper

class ParallaxActivity : AppCompatActivity() {

    private val FRAGMENT_WELCOMEANIM = 0
    private val FRAGMENT_LOGINANIM = 1

    private var iv_logo: ImageView? = null
    var vp_parent: ParentViewPager? = null

    private var mLogoY: Float = 0.toFloat()
    private var mAnimatorSet: AnimatorSet? = null

    private var mWelcomAnimFragment: WelcomAnimFragment? = null
    private var mLoginAnimFragment: LoginAnimFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        findView()
        setClick()
        init()
    }

    private fun findView() {
        iv_logo = findViewById(R.id.iv_logo) as ImageView
        vp_parent = findViewById(R.id.vp_parent) as ParentViewPager
    }

    private fun setClick() {
        vp_parent!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i2: Int) {}

            override fun onPageSelected(i: Int) {
                when (i) {
                    FRAGMENT_WELCOMEANIM -> {
                    }
                    FRAGMENT_LOGINANIM -> {
                        ParentViewPager.mLoginPageLock = true
                        iv_logo!!.postDelayed({
                            if (mLogoY == 0f) {
                                mLogoY = ViewHelper.getY(iv_logo)
                            }
                            playLogoInAnim()
                        }, 500)
                        vp_parent!!.postDelayed({ mLoginAnimFragment!!.playInAnim() }, 300)
                    }
                }
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })
    }

    private fun init() {
        val adapter = ParentFragmentStatePagerAdapter(supportFragmentManager)
        vp_parent!!.adapter = adapter
    }

    private fun playLogoInAnim() {
        val anim1 = ObjectAnimator.ofFloat(iv_logo, "scaleX", 1.0f, 0.5f)
        val anim2 = ObjectAnimator.ofFloat(iv_logo, "scaleY", 1.0f, 0.5f)
        val anim3 = ObjectAnimator.ofFloat(iv_logo, "y", mLogoY.toFloat(), DisplayUtil.dip2px(this@ParallaxActivity, 15f).toFloat())

        if (mAnimatorSet != null && mAnimatorSet!!.isRunning) {
            mAnimatorSet!!.cancel()
            mAnimatorSet = null
        }
        mAnimatorSet = AnimatorSet()
        mAnimatorSet!!.play(anim1).with(anim2)
        mAnimatorSet!!.play(anim2).with(anim3)
        mAnimatorSet!!.duration = 1000
        mAnimatorSet!!.start()
    }

    inner class ParentFragmentStatePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


        override fun getItem(position: Int): Fragment? {
            when (position) {
                FRAGMENT_WELCOMEANIM -> {
                    mWelcomAnimFragment = WelcomAnimFragment()
                    vp_parent!!.setWelcomAnimFragment(mWelcomAnimFragment!!)
                    mWelcomAnimFragment!!.setWelcomAnimFragmentInterface(object : WelcomAnimFragment.WelcomAnimFragmentInterface {
                        override fun onSkip() {
                            vp_parent!!.currentItem = 1
                        }
                    })
                    return mWelcomAnimFragment
                }
                FRAGMENT_LOGINANIM -> {
                    mLoginAnimFragment = LoginAnimFragment()
                    return mLoginAnimFragment
                }
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
    }

    private val mHandler = Handler()
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit === false) {
                isExit = true
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                mHandler.postDelayed({ isExit = false }, 2000)
            } else {
                finish()
                System.exit(0)
            }
        }
        return false
    }

    companion object {

        /**
         * 点击返回键退出程序
         */
        private var isExit: Boolean? = false
    }
}
