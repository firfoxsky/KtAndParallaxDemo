package com.example.pengtaoh.ktproject.fragment.outlayer

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.pengtaoh.ktproject.R
import com.example.pengtaoh.ktproject.fragment.outlayer.loginlayer.LoginFragment
import com.example.pengtaoh.ktproject.fragment.outlayer.loginlayer.RegisterFragment
import com.example.pengtaoh.ktproject.utils.DisplayUtil


/**
 * 登录层
 */
class LoginAnimFragment : Fragment() {

    internal var rl_parent: RelativeLayout? = null
    internal var mLoginFragment: Fragment? = null
    internal var mRegisterFragment: Fragment? = null
    internal var mViewPager: ViewPager? = null
    private var mLoginText: TextView? = null
    private var mRegisterText: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_welcomanim_login, null) as ViewGroup
        rl_parent = view.findViewById(R.id.rl_parent) as RelativeLayout
        mViewPager = view.findViewById(R.id.view_pager_fragment) as ViewPager
        mLoginFragment = LoginFragment()
        mRegisterFragment = RegisterFragment()
        mLoginText = view.findViewById(R.id.tv_Login) as TextView
        mRegisterText = view.findViewById(R.id.tv_reg) as TextView
        mRegisterText!!.setOnClickListener { mViewPager!!.currentItem = 1 }
        mLoginText!!.setOnClickListener { mViewPager!!.currentItem = 0 }
        mViewPager!!.adapter = ContainerAdapter(fragmentManager)
        mViewPager!!.setOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    mLoginText!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.mipmap.ic_triangle)
                    mRegisterText!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                } else {
                    mLoginText!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    mRegisterText!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.mipmap.ic_triangle)
                }
            }
        })
        return view
    }

    fun playInAnim() {
        rl_parent!!.visibility = View.VISIBLE

        val mAnimatorSet: AnimatorSet
        val anim3 = ObjectAnimator.ofFloat(rl_parent,
                "y", DisplayUtil.getDisplayheightPixels(activity).toFloat(), DisplayUtil.dip2px(activity, 160f).toFloat())

        mAnimatorSet = AnimatorSet()
        mAnimatorSet.play(anim3)
        mAnimatorSet.setDuration(1000)
        mAnimatorSet.start()
    }

    inner class ContainerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when (position) {
                0 -> return mLoginFragment
                1 -> return mRegisterFragment
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }
    }
}
