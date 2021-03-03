package com.xmcc.ktsample.basic
import com.xmcc.ktsample.basic.ForFresh as f1
import com.xmcc.ktsample.basic.testTopFunction
/**
 * 包 和 导入
 */
class PackageAndImport {
    fun test01(): Unit {
        //当类名有冲突时，可以通过as给类名增加一个alias
        f1.ForFresh_test01

        //可以通过import导入顶层函数
        testTopFunction()
    }
}

fun main(args: Array<String>) {

}