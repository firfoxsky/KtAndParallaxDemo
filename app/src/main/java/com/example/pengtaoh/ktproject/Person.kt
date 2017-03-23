package com.example.pengtaoh.ktproject

/**
 * Created by lyh on 2016/11/15.
 */
public class Person {
    var name: String = ""
    var age: Int = 0
    var college: String? = null

    override fun toString(): String {
        println(name + "," + age + "," + college)
        return super.toString()
    }
}