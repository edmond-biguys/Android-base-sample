package com.cym.sample.threadtest

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 * Created by caoj on 2024/1/22.
 */
class ThreadWaitTest {
}
private val lock = ReentrantLock()
private val condition = lock.newCondition()

fun testA01() {
    // 启动子线程
    val childThread = Thread {
        println("child start ${System.currentTimeMillis()}")
        // 执行操作
        Thread.sleep(2000)
        // 通知主线程
        lock.withLock {
            condition.signal()
        }
    }

    // 等待子线程执行完毕
    childThread.start()
    // 等待条件满足
    lock.withLock {
        condition.await()
    }

    // 主线程继续执行
    println("主线程继续执行 ${System.currentTimeMillis()}")
}
fun main(args: Array<String>) {
    //testA01()
    ThreadWaitTestJava.testJ01()
}
