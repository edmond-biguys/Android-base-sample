package com.cym.sample.designpattern.delegate

class TonyCoder: ICodeWork {
    override fun writeCode() {
        println("实际干活的人，tony，正在开发。。。")
    }
}