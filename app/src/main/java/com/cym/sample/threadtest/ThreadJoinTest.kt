package com.cym.sample.threadtest

/**
 * Created by caoj on 2024/1/22.
 */
class ThreadTest {
}

fun main(args: Array<String>) {
    test01()
}

//1. 等待子线程执行完，再继续主线程执行
fun test01() {
    val threadChild = Thread {
        //执行耗时操作
        for (i in 0..1000) {
            print("i is $i")
        }
    }
    threadChild.start()
    println("child thread started")
    threadChild.join()
    //等待child执行完成，主线程继续执行
    println("child thread finished")

}