package chapter_1_stackandqueue

import java.util.*

/**
 * 用一个栈实现另一个栈的排序
 */
fun sortStackByStack(stack: Stack<Int>) {
    val helpStack = Stack<Int>()

    while (!stack.empty()) {
        val cur = stack.pop()

        while (!helpStack.empty() && cur > helpStack.peek()) {
            stack.push(helpStack.pop())
        }

        helpStack.push(cur)
    }

    // 将help栈的数据逆序填充到原始栈中
    while (!helpStack.empty()) {
        stack.push(helpStack.pop())
    }
}

fun main() {
    val stack = Stack<Int>()
    stack.push(3)
    stack.push(2)
    stack.push(5)
    stack.push(7)
    stack.push(1)

    printStack(stack)

    sortStackByStack(stack)

    printStack(stack)
}