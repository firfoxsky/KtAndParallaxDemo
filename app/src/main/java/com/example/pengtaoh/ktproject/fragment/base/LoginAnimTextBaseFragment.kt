package com.example.pengtaoh.ktproject.fragment.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.pengtaoh.ktproject.R
import com.example.pengtaoh.ktproject.bean.TextBean


/**
 * 下层文字滚动fragment基类
 */
class LoginAnimTextBaseFragment(tb: TextBean?) : Fragment() {

    internal var tv_title: TextView? = null
    internal var tv_content: TextView? = null

    internal var mId: Int = 0
    internal var mTitle: String? = null
    internal var mContent: String? = null

    init {
        if (tb != null) {
            mId = tb.mId
            mTitle = tb.mTitle
            mContent = tb.mContent
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_welcomanim_text, null) as ViewGroup
        tv_title = view.findViewById(R.id.tv_title) as TextView
        tv_content = view.findViewById(R.id.tv_content) as TextView
        tv_title!!.text = mTitle
        tv_content!!.text = mContent
        return view
    }
}
