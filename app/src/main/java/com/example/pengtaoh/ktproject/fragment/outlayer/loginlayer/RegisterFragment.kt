package com.example.pengtaoh.ktproject.fragment.outlayer.loginlayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pengtaoh.ktproject.R


/**
 * 注册
 */
class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_new_register, null)
        return view
    }

}
