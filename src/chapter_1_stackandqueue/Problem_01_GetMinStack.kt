package chapter_1_stackandqueue

import java.lang.StringBuilder
import java.util.*

/**
 * 设计一个有getMing功能的栈
 *
 * 题目：实现一个特殊的栈，在实现栈的基本功能基础上，在实现返回栈中最小元素的操作.
 *
 * 要求：1.push，pop，getMin操作的时间复杂度都是O(1)。
 *      2.设计的栈类型可以使用现有的栈结构。
 */
class MyStack1 {
    private val mStackData = Stack<Int>()
    private val mStackMin = Stack<Int>()

    fun push(item: Int) {
        // 数据直接进入保存数据的栈
        mStackData.push(item)

        if (mStackMin.isEmpty()) {
            mStackMin.push(item)
        } else if (item <= mStackMin.peek()) {
            // 注意判断条件为"<="，而不是"<"
            mStackMin.push(item)
        }
    }

    fun pop(): Int {
        if (mStackData.isEmpty()) {
            throw RuntimeException("StackData is empty.")
        }

        val value = mStackData.pop()

        if (value == this.getMin()) {
            mStackMin.pop()
        }

        return value
    }

    fun getMin(): Int {
        if (mStackMin.isEmpty()) {
            throw RuntimeException("StackMin is empty.")
        }

        return mStackMin.peek()
    }

    fun printStack() {
        val sb = StringBuilder()
        mStackData.forEach {
            sb.append("$it ")
        }

        print("MinData: ${this.getMin()} -> ")
        println("StackData: $sb")
    }
}

class MyStack2 {
    private val mStackData = Stack<Int>()
    private val mStackMin = Stack<Int>()

    fun push(item: Int) {
        // 数据直接进入保存数据的栈
        mStackData.push(item)

        if (mStackMin.isEmpty()) {
            mStackMin.push(item)
        } else if (item <= mStackMin.peek()) {
            // 注意判断条件为"<="，而不是"<"
            mStackMin.push(item)
        } else {
            mStackMin.push(mStackMin.peek())
        }
    }

    fun pop(): Int {
        if (mStackData.isEmpty()) {
            throw RuntimeException("StackData is empty.")
        }

        mStackMin.pop()

        return mStackData.pop()
    }

    fun getMin(): Int {
        if (mStackMin.isEmpty()) {
            throw RuntimeException("StackMin is empty.")
        }

        return mStackMin.peek()
    }

    fun printStack() {
        val sb = StringBuilder()
        mStackData.forEach {
            sb.append("$it ")
        }

        print("MinData: ${this.getMin()} -> ")
        println("StackData: $sb")
    }
}

fun main(args: Array<String>) {
    val myStack1 = MyStack1()
    myStack1.push(3)
    myStack1.push(5)
    myStack1.push(1)
    myStack1.printStack()
    myStack1.pop()
    myStack1.printStack()

    println("==============================")

    val myStack2 = MyStack1()
    myStack2.push(3)
    myStack2.push(5)
    myStack2.push(1)
    myStack2.printStack()
    myStack2.pop()
    myStack2.printStack()
}