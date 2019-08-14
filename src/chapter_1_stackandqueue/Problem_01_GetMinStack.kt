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
abstract class MyStack {
    protected val mStackData = Stack<Int>()
    protected val mStackMin = Stack<Int>()

    abstract fun push(item: Int)
    abstract fun pop(): Int
    abstract fun getMin(): Int

    fun printStack() {
        val sb = StringBuilder()
        mStackData.forEach {
            sb.append("$it ")
        }

        print("MinData: ${this.getMin()} -> ")
        println("StackData: $sb")
    }
}

class MyStack1 : MyStack() {
    override fun push(item: Int) {
        // 数据直接进入保存数据的栈
        mStackData.push(item)

        if (mStackMin.isEmpty()) {
            mStackMin.push(item)
        } else if (item <= mStackMin.peek()) {
            // 注意判断条件为"<="，而不是"<"
            mStackMin.push(item)
        }
    }

    override fun pop(): Int {
        if (mStackData.isEmpty()) {
            throw RuntimeException("StackData is empty.")
        }

        val value = mStackData.pop()

        if (value == this.getMin()) {
            mStackMin.pop()
        }

        return value
    }

    override fun getMin(): Int {
        if (mStackMin.isEmpty()) {
            throw RuntimeException("StackMin is empty.")
        }

        return mStackMin.peek()
    }
}

class MyStack2 : MyStack() {
    override fun push(item: Int) {
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

    override fun pop(): Int {
        if (mStackData.isEmpty()) {
            throw RuntimeException("StackData is empty.")
        }

        mStackMin.pop()

        return mStackData.pop()
    }

    override fun getMin(): Int {
        if (mStackMin.isEmpty()) {
            throw RuntimeException("StackMin is empty.")
        }

        return mStackMin.peek()
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

    val myStack2 = MyStack2()
    myStack2.push(3)
    myStack2.push(5)
    myStack2.push(1)
    myStack2.printStack()
    myStack2.pop()
    myStack2.printStack()
}