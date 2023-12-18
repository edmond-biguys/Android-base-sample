package com.cym.sample.collections.list

interface List<T> {
    //add
    fun add(t: T)
    //add index
    fun add(index: Int, t: T)
    //get
    fun get(index: Int): T
    //remote
    fun remote(index: Int): T
    fun remote(t: T): Boolean
    //indexOf
    fun indexOf(t: T): Int
    //clear
    fun clear()
    //size
    fun size(): Int

    fun <E> iterator(): Iterator<E>
}