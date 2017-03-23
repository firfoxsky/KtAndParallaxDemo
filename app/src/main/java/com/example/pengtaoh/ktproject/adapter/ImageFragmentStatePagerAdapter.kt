package com.example.pengtaoh.ktproject.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer.LoginAnimImageFristFragment
import com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer.LoginAnimImageSecondFragment
import com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer.LoginAnimImageThridFragment
import java.util.*

/**
 * 总共上层有3页动画,写死在adapter
 */
class ImageFragmentStatePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    internal var mFragments: ArrayList<Fragment>? = null
    internal var mLoginAnimImageFristFragment: LoginAnimImageFristFragment?= null
    internal var mLoginAnimImageSecondFragment: LoginAnimImageSecondFragment?= null
    internal var mLoginAnimImageThridFragment: LoginAnimImageThridFragment?= null

    fun getFragement(position: Int): Fragment? {
        when (position) {
            0 -> return mLoginAnimImageFristFragment
            1 -> return mLoginAnimImageSecondFragment
            2 -> return mLoginAnimImageThridFragment
        }
        return null
    }

    init {
        if (mFragments == null) {
            mFragments = ArrayList<Fragment>()
            mLoginAnimImageFristFragment = LoginAnimImageFristFragment()
            mFragments!!.add(mLoginAnimImageFristFragment!!)
            mLoginAnimImageSecondFragment = LoginAnimImageSecondFragment()
            mFragments!!.add(mLoginAnimImageSecondFragment!!)
            mLoginAnimImageThridFragment = LoginAnimImageThridFragment()
            mFragments!!.add(mLoginAnimImageThridFragment!!)
        }
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return mLoginAnimImageFristFragment
            1 -> return mLoginAnimImageSecondFragment
            2 -> return mLoginAnimImageThridFragment
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }
}
