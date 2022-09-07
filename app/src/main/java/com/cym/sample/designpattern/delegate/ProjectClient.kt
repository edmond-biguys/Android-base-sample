package com.cym.sample.designpattern.delegate

/**
 * 要做项目的客户，客户和Edmond关系比较好，就把项目给了Edmond
 */
class ProjectClient {
    fun doTheCodeWork() {
        println("客户准备让代理开发人员Edmond做这个项目")
        val edmond = PersonWhoHasProject()
        edmond.writeCode()
    }

}

fun main(args: Array<String>) {
    val project = ProjectClient()
    project.doTheCodeWork()
}