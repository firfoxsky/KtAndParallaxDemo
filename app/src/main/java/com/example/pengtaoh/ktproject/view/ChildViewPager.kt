package com.example.pengtaoh.ktproject.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet

/**
 * 底层ViewPager所有事件由父层ViewPager手动分发

 */

class ChildViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {
    var mIsLockScoll = false
}
