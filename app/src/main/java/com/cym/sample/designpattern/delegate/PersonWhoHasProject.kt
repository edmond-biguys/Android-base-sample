package com.cym.sample.designpattern.delegate

/**
 * 一个有项目，但是又不想自己写代码的人（Edmond），于是，他把写代码的任务交给了开发人员tony
 * Edmond是一个proxy，实际干活的是tony。
 * 静态代理模式。
 */
class PersonWhoHasProject: ICodeWork{

    private lateinit var tonyCoder: TonyCoder

    override fun writeCode() {
        beforeWork()

        println("Edmond拿到项目后，并没有自己开发，而是交给了tony去做")
        if (!this::tonyCoder.isInitialized) {
            tonyCoder = TonyCoder()
        }

        tonyCoder.writeCode()

        afterWork()
    }

    private fun beforeWork() {
        println("Edmond和客户谈好开发费用，和tony谈好开发费用")
    }

    private fun afterWork() {
        println("开发完成，支付tony开发费用，并且赚取差价")
    }

}