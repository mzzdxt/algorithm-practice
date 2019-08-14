package chapter_1_stackandqueue

import java.lang.StringBuilder
import java.util.*

/**
 * 由两个栈组成的队列
 *
 * 题目：编写一个类，用两个栈实现队列，支持队列的基本操作（add，poll，peek）
 *
 * 要求：无
 */
class TwoStacksQueue {
    private val mStackPush = Stack<Int>()
    private val mStackPop = Stack<Int>()

    /**
     * push栈中的数据倒入pop栈
     */
    private fun push2Pop() {
        if (mStackPop.empty()) {
            while (!mStackPush.empty()) {
                mStackPop.push(mStackPush.pop())
            }
        }
    }

    fun add(item: Int) {
        mStackPush.push(item)

        push2Pop()
    }

    fun poll(): Int {
        if (mStackPush.empty() && mStackPop.empty()) {
            throw RuntimeException("Queue is empty.")
        }

        push2Pop()
        return mStackPop.pop()
    }

    fun peek(): Int {
        if (mStackPush.empty() && mStackPop.empty()) {
            throw RuntimeException("Queue is empty.")
        }

        push2Pop()
        return mStackPop.peek()
    }

    fun printInner() {
        val sbPush = StringBuilder()
        mStackPush.forEach {
            sbPush.append("$it ")
        }
        println("StackPush -> $sbPush")

        val sbPop = StringBuilder()
        mStackPop.forEach {
            sbPop.append("$it ")
        }
        println("StackPop -> $sbPop")
        println()
    }
}

fun main() {
    val queue = TwoStacksQueue()

    queue.add(1)
    queue.printInner()

    queue.add(2)
    queue.printInner()

    queue.add(3)
    queue.printInner()

    println(queue.peek())
    println(queue.poll())
    queue.printInner()

    println(queue.peek())
    println(queue.poll())
    queue.printInner()

    println(queue.peek())
    println(queue.poll())
    queue.printInner()
}