package chapter_1_stackandqueue

import extension.TreeNode
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * 构造数组的MaxTree
 */
fun getMaxTree(arr: ArrayList<Int>): TreeNode? {
    val nArr = arrayOfNulls<TreeNode>(arr.size)

    for ((index, value) in arr.withIndex()) {
        nArr[index] = TreeNode(value)
    }

    val stack = Stack<TreeNode>()
    val lBigMap = HashMap<TreeNode, TreeNode?>()
    val rBigMap = HashMap<TreeNode, TreeNode?>()

    for (node in nArr) {
        while (!stack.isEmpty() && stack.peek().value < node!!.value) {
            popStackSetMap(stack, lBigMap)
        }

        stack.push(node)
    }

    while (!stack.isEmpty()) {
        popStackSetMap(stack, lBigMap)
    }

    for (i in nArr.size - 1 downTo 0) {
        val node = nArr[i]

        while (!stack.isEmpty() && stack.peek().value < node!!.value) {
            popStackSetMap(stack, rBigMap)
        }

        stack.push(node)
    }

    while (!stack.isEmpty()) {
        popStackSetMap(stack, rBigMap)
    }

    var head: TreeNode? = null

    for (node in nArr) {
        val left = lBigMap[node]
        val right = rBigMap[node]

        when {
            left == null && right == null -> {
                // 当前为根节点
                head = node
            }

            left == null -> {
                if (right!!.left == null) {
                    right.left = node
                } else {
                    right.right = node
                }
            }

            right == null -> {
                if (left.left == null) {
                    left.left = node
                } else {
                    left.right = node
                }
            }

            else -> {
                // 左右两边都有值，取相对较小的那个节点作为父节点
                val parent = if (left.value < right.value) left else right

                if (parent.left == null) {
                    parent.left = node
                } else {
                    parent.right = node
                }
            }
        }
    }

    return head
}

fun popStackSetMap(stack: Stack<TreeNode>, map: HashMap<TreeNode, TreeNode?>) {
    val popNode = stack.pop()

    map[popNode] = if (stack.isEmpty()) {
        null
    } else {
        stack.peek()
    }
}

/**
 * 先序遍历
 */
fun printPreOrder(head: TreeNode?) {
    if (head == null) {
        return
    }

    print("${head.value} ")
    printPreOrder(head.left)
    printPreOrder(head.right)
}

/**
 * 中序遍历
 */
fun printInOrder(head: TreeNode?) {
    if (head == null) {
        return
    }

    printPreOrder(head.left)
    print("${head.value} ")
    printPreOrder(head.right)
}

fun main() {
    val head = getMaxTree(arrayListOf(3, 4, 5, 1, 2))
    println("先序遍历：")
    printPreOrder(head)
    println()
    println("中序遍历：")
    printInOrder(head)
}