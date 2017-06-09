package com.xinqushi.kotlin

import java.awt.List

/**
 * Created by yangli on 2017/5/26 - 19:09.
 */
fun main(args: Array<String>) {
    println("hello, kotlin")

    Student("韩菱纱").printName()

    val firstName : String = "柳"
    val lastName : String = "梦璃"

    println("her name is ${getName(firstName, lastName)}")


}

fun hasEmpty(vararg strArray : String?) : Boolean{
    for (str in strArray){
        str ?: return true
    }
    return false
}

fun getName(firstName : String?, lastName : String ?= "unknow") : String {
    if (hasEmpty(firstName, lastName)){
        lastName?.let { return@getName "${checkName(firstName)} $lastName" }
        firstName?.let { return@getName "$firstName ${checkName(lastName)}" }
    }
    return "$firstName$lastName"
}

fun checkName(name : String?) : String = name ?: "unknow"