package com.example.pengtaoh.ktproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private var c: String = ""
    //延迟加载
    private val message3: TextView by lazy(LazyThreadSafetyMode.NONE) {
        findViewById(R.id.message3) as TextView
    }

    // 合成属性（synthetic properties）
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        message.setText("StartNormalViewpager")
        message2.setText("P" +
                "arallaxViewpager")
        message3!!.setText("H5annotaion")

        message.setOnClickListener {
            startActivity(Intent(MainActivity@ this, NormalActivity::class.java))
        }

        message2.setOnClickListener {
            startActivity(Intent(Main2Activity@ this, ParallaxActivity::class.java))
        }

    }
}
