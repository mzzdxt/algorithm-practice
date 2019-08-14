package chapter_1_stackandqueue

import java.util.*

/**
 * 如何仅用递归函数和栈操作逆序一个栈
 *
 * 题目：一个栈依次压入1、2、3、4、5，然后利用递归函数实现栈的逆序操作，将这个栈进行转置。
 *
 * 要求：无
 */

/**
 * 获取并移除栈底的最后一个元素
 */
private fun getAndRemoveLastElement(stack: Stack<Int>): Int {
    val result = stack.pop()

    return if (stack.empty()) {
        result
    } else {
        val last = getAndRemoveLastElement(stack)
        stack.push(result)
        last
    }
}

fun reverse(stack: Stack<Int>) {
    if (stack.empty()) {
        return
    }

    val lastElement = getAndRemoveLastElement(stack)
    reverse(stack)
    stack.push(lastElement)
}

fun printStack(stack: Stack<Int>) {
    val sb = StringBuilder()
    stack.forEach {
        sb.append("$it ")
    }
    println("StackData -> $sb")
}


fun main() {
    val stack = Stack<Int>()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    stack.push(4)
    stack.push(5)

    printStack(stack)

    reverse(stack)

    printStack(stack)
}