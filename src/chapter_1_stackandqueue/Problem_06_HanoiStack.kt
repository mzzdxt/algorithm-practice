package chapter_1_stackandqueue

import java.util.*

/**
 * 用栈来求解汉诺塔问题
 *
 * 问题：修改汉诺塔游戏规则，限制不能从最左侧的塔直接移动到最右侧，也不能从最右侧的塔直接移动到最左侧，必须经过中间的塔。
 *      求当塔有N层时，打印最优移动过程和最优移动总步数。
 */
fun hanoiProblem1(num: Int, left: String, mid: String, right: String): Int {
    return if (num < 1) {
        0
    } else {
        process(num, left, mid, right, left, right)
    }
}

fun process(num: Int, left: String, mid: String, right: String, from: String, to: String): Int {
    return if (num == 1) {
        if (from == mid || to == mid) {
            println("Move $num from $from to $to")
            1
        } else {
            println("Move $num from $from to $mid")
            println("Move $num from $mid to $to")
            2
        }
    } else {
        if (from == mid || to == mid) {
            val another = if (from == left || to == left) {
                right
            } else {
                left
            }

            val part1 = process(num - 1, left, mid, right, from, another)
            val part2 = 1
            println("Move $num from $from to $to")
            val part3 = process(num - 1, left, mid, right, another, to)

            part1 + part2 + part3
        } else {
            val part1 = process(num - 1, left, mid, right, from, to)
            val part2 = 1
            println("Move $num from $from to $mid")
            val part3 = process(num - 1, left, mid, right, to, from)
            val part4 = 1
            println("Move $num from $mid to $to")
            val part5 = process(num - 1, left, mid, right, from, to)

            part1 + part2 + part3 + part4 + part5
        }
    }
}

enum class Action {
    NO, LToM, MToL, MToR, RToM
}

fun hanoiProblem2(num: Int, left: String, mid: String, right: String): Int {
    val lS = Stack<Int>()
    lS.push(Int.MAX_VALUE)

    val mS = Stack<Int>()
    mS.push(Int.MAX_VALUE)

    val rS = Stack<Int>()
    rS.push(Int.MAX_VALUE)

    for (i in num downTo 1) {
        lS.push(i)
    }

    val record = arrayOf(Action.NO)

    var stepCount = 0

    while (rS.size != num + 1) {
        stepCount += fStackTotStack(record, Action.LToM, Action.MToL, lS, mS, left, mid)
        stepCount += fStackTotStack(record, Action.MToL, Action.LToM, mS, lS, mid, left)
        stepCount += fStackTotStack(record, Action.MToR, Action.RToM, mS, rS, mid, right)
        stepCount += fStackTotStack(record, Action.RToM, Action.MToR, rS, mS, right, mid)
    }

    return stepCount
}

fun fStackTotStack(
    record: Array<Action>, curAction: Action, preNoAction: Action,
    fromStack: Stack<Int>, toStack: Stack<Int>, from: String, to: String
): Int {
    return if (record[0] != preNoAction && fromStack.peek() < toStack.peek()) {
        toStack.push(fromStack.pop())
        println("Move ${toStack.peek()} from $from to $to")
        record[0] = curAction
        1
    } else {
        0
    }
}

fun main() {
    val num = 3
    val stepCountMethod1 = hanoiProblem1(num, "left", "mid", "right")
    println("Move with method1 cost step count = $stepCountMethod1")

    val stepCountMethod2 = hanoiProblem2(num, "left", "mid", "right")
    println("Move with method2 cost step count = $stepCountMethod2")
}
