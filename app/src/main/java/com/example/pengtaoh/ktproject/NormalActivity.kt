package com.example.pengtaoh.ktproject

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

import java.util.ArrayList

class NormalActivity : AppCompatActivity() {

    private var mViewPager: ViewPager? = null

    private var mButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)

        mViewPager = findViewById(R.id.viewpager) as ViewPager
        mButton = findViewById(R.id.button) as Button
        initView()
    }

    fun initView() {
        mButton!!.visibility = View.GONE

        val views = ArrayList<ImageView>()
        val view1 = ImageView(this, null)
        view1.setBackgroundResource(R.mipmap.timg)
        views.add(view1)
        val view2 = ImageView(this)
        view2.setBackgroundResource(R.mipmap.gyy2)
        views.add(view2)
        val view3 = ImageView(this)
        view3.setBackgroundResource(R.mipmap.gyy3)
        views.add(view3)
        val view4 = ImageView(this)
        view4.setBackgroundResource(R.mipmap.gyy4)
        views.add(view4)

        mViewPager!!.adapter = MyViewPagerAdapter(views)

        mViewPager!!.setPageTransformer(true, DepthPageTransformer())

        mViewPager!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == views.size - 1) {
                    mButton!!.visibility = View.VISIBLE
                } else
                    mButton!!.visibility = View.GONE
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }


    inner class MyViewPagerAdapter(private val views: List<ImageView>?) : PagerAdapter() {

        override fun getCount(): Int {
            return views?.size ?: 0
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): ImageView {
            container.addView(views!![position])
            return views[position]
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
//            super.destroyItem(container, position, `object`)
            container!!.removeView(`object` as ImageView?)
        }
    }
}
