package com.example.pengtaoh.ktproject.fragment.outlayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.pengtaoh.ktproject.R
import com.example.pengtaoh.ktproject.adapter.ImageFragmentStatePagerAdapter
import com.example.pengtaoh.ktproject.adapter.TextFragmentStatePagerAdapter
import com.example.pengtaoh.ktproject.bean.TextBean
import com.example.pengtaoh.ktproject.fragment.base.LoginAnimImageBaseFragment
import com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer.LoginAnimImageFristFragment
import com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer.LoginAnimImageSecondFragment
import com.example.pengtaoh.ktproject.fragment.outlayer.welcomelayer.LoginAnimImageThridFragment
import com.example.pengtaoh.ktproject.view.ChildViewPager
import com.example.pengtaoh.ktproject.view.WelcomeIndicator
import java.util.*

/**
 * 动画层
 */
class WelcomAnimFragment : Fragment() {


    var imageViewPager: ChildViewPager? = null
    var textViewPager: ChildViewPager? = null
    var view_indicator: WelcomeIndicator? = null
    var tv_skip: TextView? = null


    var mOldPosition = -1
    var mIsMoveParent = false
    private var mFristPageSuperLock = false
    internal var mImageFragmentStatePagerAdapter: ImageFragmentStatePagerAdapter? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_welcomanim, null) as ViewGroup

        imageViewPager = view.findViewById(R.id.vp_imageanim) as ChildViewPager
        textViewPager = view.findViewById(R.id.vp_textanim) as ChildViewPager
        view_indicator = view.findViewById(R.id.view_indicator) as WelcomeIndicator
        tv_skip = view.findViewById(R.id.tv_skip) as TextView
//        rl_indicator = view.findViewById(R.id.rl_indicator) as RelativeLayout

        initImageFragmentViewPager()
        initTextFragmentViewPager()
        return view
    }


    private fun initImageFragmentViewPager() {
        imageViewPager!!.offscreenPageLimit = 3
        mImageFragmentStatePagerAdapter = ImageFragmentStatePagerAdapter(childFragmentManager)
        imageViewPager!!.adapter = mImageFragmentStatePagerAdapter
        imageViewPager!!.mIsLockScoll = true

        imageViewPager!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i2: Int) {}

            override fun onPageSelected(i: Int) {
                if (i == imageViewPager!!.adapter.count - 1) {
                    mIsMoveParent = true
                } else {
                    mIsMoveParent = false
                }
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })
    }

    /**
     * 初始化文字动画数据
     */
    private fun initTextFragmentViewPager() {
        val mTextBeans = ArrayList<TextBean>()
        val bean0 = TextBean()
        bean0.mId = 0
        bean0.mTitle = "欢迎来到小红书!"
        bean0.mContent = "在开始您小红书的旅途之前.一起来了解一下小红书购物笔记的几个特色吧"
        mTextBeans.add(bean0)

        val bean1 = TextBean()
        bean1.mId = 1
        bean1.mTitle = "购物笔记"
        bean1.mContent = "精选达人笔记,以及所有关注的笔记,尽收眼底."
        mTextBeans.add(bean1)

        val bean2 = TextBean()
        bean2.mId = 2
        bean2.mTitle = "福利社"
        bean2.mContent = "在这里,全世界的好东西都出触手可及!只需轻轻一点,将长草的好物收入囊中."
        mTextBeans.add(bean2)

        val bean3 = TextBean()
        bean3.mId = 3
        bean3.mTitle = "心愿单!"
        bean3.mContent = "你可以将其他人的笔记收藏起来啦!不仅如此,还可以订阅达人的心愿单."
        mTextBeans.add(bean3)

        val adapterText = TextFragmentStatePagerAdapter(childFragmentManager, mTextBeans)
        textViewPager!!.adapter = adapterText
        view_indicator!!.init(mTextBeans.size)

        /**
         * 文字动画层滑动监听
         */
        textViewPager!!.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i2: Int) {}

            override fun onPageSelected(p: Int) {
                if (mOldPosition < 0) {
                    mOldPosition = 0
                }
                view_indicator!!.play(mOldPosition, p)

                /**
                 * 上层图片动画的viewpager有3个子fragment
                 * 下层文字动画的viewpager有4个子fragment
                 * 0   1   2  -->  图片动画
                 * 0   1   2   3  -->  文字动画
                 */
                if (p == 0) {
                    imageViewPager!!.mIsLockScoll = true
                    if (mOldPosition == 1) {
                        mFristPageSuperLock = true
                    }
                } else if (p == 1) {
                    imageViewPager!!.mIsLockScoll = false
                    if (mFristPageSuperLock) {
                        mFristPageSuperLock = false
                    } else {
                        reset()
                        val fragment = mImageFragmentStatePagerAdapter!!.getFragement(0) as LoginAnimImageFristFragment
                        fragment.playInAnim()
                    }
                } else if (p == 2) {
                    imageViewPager!!.mIsLockScoll = false
                    reset()
                    val fragment = mImageFragmentStatePagerAdapter!!.getFragement(1) as LoginAnimImageSecondFragment
                    fragment.playInAnim()
                } else if (p == 3) {
                    imageViewPager!!.mIsLockScoll = false
                    reset()
                    val fragment = mImageFragmentStatePagerAdapter!!.getFragement(2) as LoginAnimImageThridFragment
                    fragment.playInAnim()
                } else {
                    imageViewPager!!.mIsLockScoll = false
                    reset()
                }
                mOldPosition = p
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })
    }

    /**
     * 非当前展示动画页,停止动画,回复初始状态
     */
    fun reset() {
        if (mOldPosition > 0) {
            val fragment = mImageFragmentStatePagerAdapter!!.getFragement(mOldPosition - 1) as LoginAnimImageBaseFragment
            fragment.reset()
        }
    }

    /**
     * 跳过监听
     */
    var mWelcomAnimFragmentInterface: WelcomAnimFragmentInterface? = null

    fun setWelcomAnimFragmentInterface(mInterface: WelcomAnimFragmentInterface) {
        this.mWelcomAnimFragmentInterface = mInterface
    }

    interface WelcomAnimFragmentInterface {
        fun onSkip()
    }
}
