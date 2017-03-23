package com.example.pengtaoh.ktproject.fragment.outlayer.loginlayer

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pengtaoh.ktproject.R


/**
 * 登录
 */
class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_new_login, null)
        view.findViewById(R.id.tv_down).setOnClickListener { gotoRankMe() }
        return view
    }

    /**
     * 下载正式版小红书
     */
    private fun gotoRankMe() {
        try {
            val uri = Uri.parse("market://details?id=com.xingin.xhs")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
        }

    }
}
