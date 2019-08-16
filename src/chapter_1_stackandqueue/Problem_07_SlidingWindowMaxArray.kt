package chapter_1_stackandqueue

import java.util.*

/**
 * 生成窗口最大值数组
 */
fun getMaxWindow(arr: IntArray, w: Int): Array<Int?>? {
    if (w < 1 || arr.size < w) {
        return null
    }

    // 用于记录下标的双端队列
    val queueMax = LinkedList<Int>()
    // 初始化用于记录最大值的数组
    val result = arrayOfNulls<Int>(arr.size - w + 1)

    var index = 0
    for (i in 0 until arr.size) {
        while (!queueMax.isEmpty() && arr[queueMax.peekLast()] <= arr[i]) {
            // queueMax中存放的最后一个下标对应的值小于等于正在遍历的下标对应的值，出列
            queueMax.pollLast()
        }

        queueMax.addLast(i)

        // queueMax中最多存放w+1个元素
        if (queueMax.peekFirst() == i - w) {
            // 如果queueMax队头的下标为i-w，说明queueMax队头的下标已过期，
            queueMax.pollFirst()
        }

        // 仅从大于等于w个元素以后，开始记录最大值
        if (i >= w - 1) {
            result[index++] = arr[queueMax.peekFirst()]
        }
    }

    return result
}

fun printArray(arr: Array<Int?>?) {
    arr?.forEach {
        print("$it ")
    } ?: print("Null Array!")

    println()
}

fun main() {
    val arr = intArrayOf(4, 3, 5, 4, 3, 3, 6, 7)
    val w = 3
    printArray(getMaxWindow(arr, w))
}