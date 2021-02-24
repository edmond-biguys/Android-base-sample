package com.xmcc.ktsample.basic

import kotlin.random.Random

class ForFresh {
    //基本语法、语句用法
    /*
    基本语法、函数、变量定义、字符串写法、
    条件表达式、空值及检测、类型转换、循环语句、
    when表达式、区间使用、集合
     */
    //基本语法，nothing to say

    //函数
    fun buyFreshMeat(price: Double): String {
        val weight = 1.1
        return "$weight kg"
    }
    //将表达式式作为函数体
    fun sum(a: Int, b: Int) = a + b

    //条件表达式
    fun conditionalStatement() {
        if (Random(10).nextInt() % 2 == 0) {
            println("can divide by 2")
        } else {
            println("cannot divide by 2")
        }

        //直接使用表达式来赋值
        val index = if (Random(10).nextInt() % 2 == 0) 1 else 0
    }

    //直接将条件表达式作为函数体
    fun max(a: Int, b: Int) = if (a > b) a else b

    //可空对象的声明
    fun aNullStory(a: Any) {
        val s1: String? = null
        //调用前先判空
        println("s1?.length ${s1?.length}")

        //强制调用
//        println("s1!!.length ${s1!!.length}")

        //类型检测
        if (a is Int) {
            println("a is int")
        } else {
            println("a is not int")
        }

        if (a !is Int) {
            println("a is not int")
        } else {
            println("a is int")
        }

    }

    //循环语句 for
    fun loopStatementFor() {
        val list = listOf("a", "b", "c")

        println("item in list")
        for (item in list) {
            print("$item ,")
        }
        println()

        println("index in list.indices")
        for (index in list.indices) {
            print(list[index])
            print("${if (index == list.size - 1) ' ' else ','}")
        }

        println()
        println("index in 1..7")
        for (index in 1..7) {
            print(index)
            print(",")
        }

        println()
        println("index in 7 downTo 1")
        for (index in 7 downTo 1) {
            print(index)
            print(",")
        }

        println()
        println("step 2")
        for (index in 1..7 step 2) {
            print(index)
            print(",")
        }

        println()
        println("不含尾部")
        for (index in 1 until 7) {
            print(index)
            print(",")
        }

        println()
        println("遍历list 同时取出index ")
        for ((index, e) in list.withIndex()) {
            print("index $index  element $e")
            print(", ")
        }

    }

    //while，do..while语句与java一样
    // return，默认从当前最直接包围他的函数或者匿名函数退出
    // break，终止当前包围他的循环
    // continue

    //when语句
    fun howToUseWhen(obj: Any): String =
        when (obj) {
            1 -> "One"
            in 2..10 -> obj.toString()
            11, 12 -> obj.toString()
            "hello" -> "hello"
            is Long -> "this is long type"
            !is String -> "this is not a string"
            else -> "unknown"
        }

    fun scopeFunctionInstruction() {
        //let with run 使用it
        //apply also 使用this
        Person("cao", "jian", 20).let {
            println(it)
            it.age = 21
            println(it)
        }
        val s1: String? = "abc"
        s1?.let {
            println(it.toString())
        }

        val numbers = mutableListOf("one", "two", "three", "four", "five")
        val result = numbers.map { "$it abc" }.map { "$it cd" }.filter { it.length > 5 }
        println(result)
    }

}

class Person(
    val firstName: String,
    val lastName: String,
    var age: Int,
) {
    override fun toString(): String {
        return "$firstName $lastName is $age"
    }
}

fun main(args: Array<String>) {
    val forFresh = ForFresh()
//    forFresh.aNullStory(1.0)
//    forFresh.loopStatementFor()
    forFresh.scopeFunctionInstruction()
}