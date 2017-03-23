package com.example.pengtaoh.ktproject.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

import com.example.pengtaoh.ktproject.bean.TextBean
import com.example.pengtaoh.ktproject.fragment.base.LoginAnimTextBaseFragment

import java.util.ArrayList


/**
 * 下层文字动画有4页
 */
class TextFragmentStatePagerAdapter(fm: FragmentManager, tbs: ArrayList<TextBean>?) : FragmentStatePagerAdapter(fm) {

    internal var mTextBeans: ArrayList<TextBean>

    init {
        if (tbs != null) {
            mTextBeans = tbs
        } else {
            mTextBeans = ArrayList<TextBean>()
        }
    }

    override fun getItem(position: Int): Fragment {
        return LoginAnimTextBaseFragment(mTextBeans[position])
    }

    override fun getCount(): Int {
        return mTextBeans.size
    }
}
