package com.cym.sample.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.math.BigInteger

class FlowSample {

    fun testFlow() {

    }

    fun fibonacci(): Flow<BigInteger> = flow {
        var x = BigInteger.ZERO
        var y = BigInteger.ONE
        while (true) {
            emit(x)
            println("x=$x, y=$y")
            //0,1,1,2,3,5
            // x  y
            // 0  1
            // 1  1
            // 1  2
            // 2
            x = y.also {
                y += x
            }
            // x  y
            // 1  1
            // 1  2
            // 1  2
            // 2
            if (x == BigInteger.valueOf(50)) return@flow
        }
    }

}

fun main(args: Array<String>) {
    //FlowSample().fibonacci()
    var x = 0
    var y = 1
//    while (true) {
//        println("x=$x, y=$y")
//        x = y.also {
//            y += x
//        }
//        if (x > 30) return
//
//    }

    println("x=$x")
}