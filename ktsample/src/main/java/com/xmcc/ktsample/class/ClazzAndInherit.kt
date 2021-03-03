package com.xmcc.ktsample.`class`

class ClazzAndInherit {

    //属性、init代码块会按照顺序执行
    val a0 = "-2".also { println(it) }
    val a1 = "-1".also { println(it) }
    init {
        println("a0 $a0")
        println("0")
    }
    val a2 = "1".also { println(it) }

    init {
        println("2")
    }



}

fun main(args: Array<String>) {
    val clazzAndInherit = ClazzAndInherit()
}