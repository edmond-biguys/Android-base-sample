package com.xmcc.ktsample.basic

import java.math.BigDecimal
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

        val number = Random.nextInt(100)
        val takeIfNumber: Int? = number.takeIf { it % 2 == 0 }
        val takeUnlessNumber = number.takeUnless { it % 2 == 0 }
        val abc = takeIfNumber.toString()
        println("number $number takeIf $takeIfNumber takeUnless $takeUnlessNumber")
        repeat(2) {
            println(it)
        }

    }

    //浮点数
    fun testFloatData() {
        var f1: Float = 1.23456f
        println(f1)
        f1 = 1.234567f
        println(f1)
        f1 = 1.2345678f
        println(f1)
        f1 = 1234.01266567890123456789123456789123456789f
        println(f1)
        var d1: Double = 12345.01234567891204567890123456789123456789
        println(d1)
        d1 = 123456789012345678901234567890.0
        println(d1)
        var d2: BigDecimal = BigDecimal("12345678901234567890123456789012345678901234567890.0")
        var d3: BigDecimal = BigDecimal("0.1")
        println(d2)
        println(d3)
        println(d2 + d3)
        //float 结构
        //sign    exponent      mantissa
        // 1        8               23
        //float 有效位数为 2^23 -> 6-7位有效数字，显示的第8位不是准确数值

        //double 结构
        //sign    exponent      mantissa
        // 1        11               52
        //float 有效位数为 2^52 -> 15-16位有效数字，显示的第17位不是准确数值

    }

    //基本类型
    //数字 整数 Byte 8, Short 16, Int 32, Long 64
    fun testNumber() {
        //显示转换
        val i1 = "100".toInt()
        val l1: Long = 1.0.toLong()
        val f1: Float = 1.toFloat()
        //隐式转换
        val l2 = 2L + 1
        val d2 = 2.0 + 1
    }

    //位运算
    /*
    shl 有符号左移
    shr 有符号右移
    ushr 无符号右移
    and 位与
    or 位或
    xor 位异或
    inv 位非
     */
    fun bitOperation() {
        //shl 有符号左移 0000 0001 -> 0000 0100
        println(-1 shl 2)
        //shr 有符号右移
        println(-7 shr 1)
        println(-9 shr 1)
        //println(-7/2)

//        println(1 and  3)
//        println(1 or 2)
//        println(1 xor 2)
//        println(1 xor 5)
//        println("------")
//        println(0.inv())
//        println(1.inv())
//        println(2.inv())
//        println(3.inv())
//        println(4.inv())
//        println(5.inv())
//        println(6.inv())
//        println(7.inv())
//        println(8.inv())
//        println(9.inv())
//        val i1: Int = 3
//        //println(i1.inv())
//        //wrap unary operator and value with ()
//        println("------")
//        println((-1).inv())
//        println((-2).inv())
//        println((-3).inv())
//        println((-4).inv())
//        println((-5).inv())
//        println((-6).inv())
//        println((-7).inv())
//        println((-8).inv())
//        println((-9).inv())
    }

    fun testString() {
        //原始字符串，用三个引号表示，
        println(""" abc
        > dflsdjfls
        | djfladjflasd
        | 
    """.trimMargin(">"))

        println(""" abc
        > dflsdjfls
        | djfladjflasd
        | 
    """.trimMargin()) //默认为｜
    }

    companion object  {
        val ForFresh_test01 = "abc"
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

fun testTopFunction() {
    println("this is top function")
}

fun main(args: Array<String>) {
    val forFresh = ForFresh()
//    forFresh.aNullStory(1.0)
//    forFresh.loopStatementFor()
//    forFresh.scopeFunctionInstruction()
//    forFresh.testFloatData()
//    forFresh.bitOperation()
    forFresh.testString()

}