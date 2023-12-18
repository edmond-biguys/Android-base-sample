package com.cym.sample.collections.list

import java.util.Arrays

class ArrayList<T>: List<T> {

    private val DEFAULT_CAPACITY = 10

    private var objArray: Array<Any?> = arrayOfNulls(DEFAULT_CAPACITY)

    private var capacity = 0
    private var size = 0

    constructor() {
        capacity = DEFAULT_CAPACITY
        objArray = arrayOfNulls(capacity)
    }
    constructor(size: Int) {
        capacity = 1
        //把capacity设置为大于size的最小的2的n次方
        //比如size=5, capacity=8；size=9, capacity=16
        while (capacity < size) {
            capacity = capacity shl 1
        }
        objArray = arrayOfNulls(capacity)
    }

    /*
    扩容
     */
    private fun ensureSize(expectCapacity: Int) {
        //如果期望的容量大于当前容量，就扩容
        if (expectCapacity > capacity) {
            //把capacity设置为大于expectCapacity的最小的2的n次方
            //比如expectCapacity=5, capacity=8；expectCapacity=9, capacity=16
            while (capacity < expectCapacity) {
                capacity  = capacity shl 1
            }
            objArray = objArray.copyOf(capacity)
        }

    }

    /*
    检查是否越界
     */
    private fun checkIndexOutOfBounds(index: Int, size: Int) {
        if (index < 0 || index > size) {
            throw IndexOutOfBoundsException("index: $index, size: $size")
        }
    }

    /*
    向最后一个位置添加元素
     */
    override fun add(t: T) {
        add(size, t)
    }

    /*
    在指定位置添加元素
     */
    override fun add(index: Int, t: T) {
        checkIndexOutOfBounds(index, size + 1)
        ensureSize(size + 1)
        //把index后面的元素都往后移动一位
        System.arraycopy(objArray, index, objArray, index + 1, size - index)
        //把t放到index位置
        objArray[index] = t
        size++
    }

    /*
    获取指定位置的元素
     */
    override fun get(index: Int): T {
        checkIndexOutOfBounds(index, size - 1)
        return objArray[index] as T
    }

    /*
    删除指定位置的元素，并返回这个元素
     */
    override fun remote(index: Int): T {
        checkIndexOutOfBounds(index, size -1)
        val oldElement = objArray[index] as T
        //把index后面的元素都往前移动一位，如果index已经是最后一个元素了，就不用移动了
        //比如index=2，size=5，把[3,4]移动到[2,3]
        if (index < size -1) {
            System.arraycopy(objArray, index + 1, objArray, index, size - index - 1)
        }
        //把最后一个元素置为null，防止内存泄露
        size--
        objArray[size] = null
        return oldElement
    }

    /*
    清空
     */
    override fun clear() {
        Arrays.fill(objArray, null)
        size = 0
    }

    override fun size(): Int {
        return size
    }

    /*
    获取指定元素的位置
     */
    override fun indexOf(t: T): Int {
        for (i in 0 until size) {
            if (objArray[i] == t) {
                return i
            }
        }
        return -1
    }

    /*
    删除指定元素
     */
    override fun remote(t: T): Boolean {
        val index = indexOf(t)
        if (index != -1) {
            remote(index)
        }
        return index != -1
    }

    override fun <T> iterator(): Iterator<T> {
        return MyIterator()
    }

    inner class MyIterator<T>: Iterator<T> {
        private var index = 0

        override fun hasNext(): Boolean {
            return index < size()
        }

        override fun next(): T {
            index++
            return get(index) as T
        }


    }

}