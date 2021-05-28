package com.xmcc.ktsample.`class`

import java.util.*

open class ClazzAndInheritance constructor(name: String = "json") {
    //主构函数不想外部看到时，可以用private声明
    /*
    次构造函数
    属性、init代码块在主构中调用，次构会优先调用主构
    所以调用顺序是按代码写的顺序调用 属性、init代码块，然后再跳用次构中的代码
     */
    constructor(name: String, age: Int): this(name) {
        println("secondary constructor")
    }
    //属性、init代码块会按照顺序执行
    val a0 = "-2".also { println(it) }
    val a1 = "-1".also { println(it) }
    init {
        println("a0 $a0")
        println("0")
    }
    val a2 = "1".also { println(it) }

    init {
        //initializer blocks
        println("2")
    }

    fun testInherit() {
        val any: Any? = null
    }



}

class Foo: ClazzAndInheritance() {
    //if you want inherit from one class, make sure that class uses the keyword 'open'
}

abstract class Animal {
    abstract fun run()
    abstract fun eat()
    open fun swim() {
        println("animal can swim")
    }
}
class Dog: Animal() {
    override fun run() {
        TODO("Not yet implemented")
    }

    override fun eat() {
        TODO("Not yet implemented")
    }
    override fun swim() {

    }

}

class CompanionSample {
//    companion object Pig {
//        fun eat(food: String) {
//            println("pig eat $food")
//        }
//    }
    //only one companion object allowed per class
    companion object { //可以省略伴生对象的名称

        fun eat(food: String) {
            println("pig eat $food")
        }
    }
}

class CompanionSampleForStatic {
    companion object {
        @JvmStatic
        fun jump(height: Int) {
            println("you can jump $height high")
        }
    }
}

class PropertySample {
    //属性声明完整表达
    /*
    var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]
     */

    var abc: String = ""
        set(value) {
                field = if (value.isNotEmpty()) {
                    value
                } else {
                    "abc123"
                }
       }

    var word: String = ""
        get() = this.toString()
//        set(value) {
//            //setDataFromString(value) // 解析字符串并赋值给其他属性
//        }
//        get() = this`.toString()
        set(value) {
          //field = value
//            field = value
    field = value
}

    var stringRepresentation: String
        get() = this.toString()
        set(value) {
            //setDataFromString(value) // 解析字符串并赋值给其他属性
        }
}

//编译器常量, 位于顶层或者是 object 声明 或 companion object 的一个成员
const val ACTION_START = 1
object Const{
    const val ACTION_DOING = 2
}
class ConstSample {
    companion object {
        const val ACTION_STOP = 3
    }

}

//延迟初始化
class LateinitSample {
    lateinit var time: String

    fun askTime() {
        println("before init ${this::time.isInitialized}")

        time = "20:20:20"
        println("after init ${this::time.isInitialized}")

        println("now is $time")
    }
}


//interface
interface Named {
    val name: String
    fun write()
}
interface Person: Named {
    val firstName: String
    val lastName: String
    fun fight()
    fun sleep() {
        println("$name is sleeping")
    }

    override fun write() {
        println("$name is writing")
    }
    override val name: String
        get() = "$firstName $lastName"
}

class Employee(override val firstName: String, override val lastName: String) : Person {
    override fun fight() {
        println("$name is fighting")
    }
    fun work() {
        println("$name is working")
    }

    override fun sleep() {
        super.sleep()
        println("$name is sleeping 2")
    }
}


class FunctionExecutor{

    fun testCompanion() {
        CompanionSample.eat("grass")
        val c = CompanionSample.Companion
    }

    fun testConst(action: Int) {
        when(action) {
            ACTION_START -> println("start")
            Const.ACTION_DOING -> println("doing")
            ConstSample.ACTION_STOP -> println("stop")
        }
    }
}

fun test02(b: Boolean) {
    println(b)
}

fun main(args: Array<String>) {
//    val clazzAndInherit1 = ClazzAndInherit("tim")
//    val clazzAndInherit2 = ClazzAndInheritance()
//    val clazzAndInherit = ClazzAndInheritance("tim", 1)

    val employee = Employee("caoj", "jian")
    employee.work()
    employee.fight()
    employee.sleep()
    val b1 = false
    val b2 = true
    test02(!b1)
    test02(b2)

}