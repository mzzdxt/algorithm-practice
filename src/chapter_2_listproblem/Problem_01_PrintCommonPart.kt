package chapter_2_listproblem

import extension.ListNode
import extension.printLinkedList

/**
 * 打印两个有序链表的公共部分
 */
fun printCommonPart(list1: ListNode?, list2: ListNode?) {
    print("Common part: ")

    var head1 = list1
    var head2 = list2

    while (head1 != null && head2 != null) {
        when {
            head1.value < head2.value -> head1 = head1.next
            head1.value > head2.value -> head2 = head2.next

            else -> {
                print("${head1.value} ")

                head1 = head1.next
                head2 = head2.next
            }
        }
    }

    println()
}

fun main() {
    val node1 = ListNode(1)
    node1.next = ListNode(3)
    node1.next!!.next = ListNode(5)
    node1.next!!.next!!.next = ListNode(7)
    node1.next!!.next!!.next!!.next = ListNode(9)

    val node2 = ListNode(1)
    node2.next = ListNode(2)
    node2.next!!.next = ListNode(3)
    node2.next!!.next!!.next = ListNode(4)
    node2.next!!.next!!.next!!.next = ListNode(5)

    node1.printLinkedList()
    node2.printLinkedList()
    printCommonPart(node1, node2)
}