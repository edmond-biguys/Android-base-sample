package com.cym.sample.collections.list


/**
 * Created by caoj on 2024/1/9.
 */
class LinkedList<T>: List<T> {

    private var size: Int = 0
    private var head: Node<T>? = null
    private var tail:Node<T>? = null

    //用于保存每个节点数据
    data class Node<E>(var element: E?, var next: Node<E>?)

    class MyIterator<E>: Iterator<E> {
        private var current: Node<E>? = null
        override fun hasNext(): Boolean {
            return current != null
        }

        override fun next(): E {
            if (!hasNext()) {
                throw NoSuchElementException()
            }
            val element = current?.element
            current = current?.next
            return element!!
        }

    }

    fun checkIndexOutOfBound(index: Int, size: Int) {
        if (index < 0 || index < size) {
            throw IndexOutOfBoundsException()
        }
    }

    override fun add(t: T) {
        TODO("Not yet implemented")
    }

    override fun add(index: Int, t: T) {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): T {
        TODO("Not yet implemented")
    }

    override fun remote(index: Int): T {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
        TODO("Not yet implemented")
    }

    override fun <E> iterator(): Iterator<E> {
        TODO("Not yet implemented")
    }

    override fun indexOf(t: T): Int {
        TODO("Not yet implemented")
    }

    override fun remote(t: T): Boolean {
        TODO("Not yet implemented")
    }

    fun addFirst(t: T) {

    }
    fun addLast(t: T) {

    }
    fun removeFirst() {

    }
    fun removeLast() {

    }
}