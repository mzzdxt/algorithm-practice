package chapter_2_listproblem

import extension.ListNode
import extension.printLinkedList
import kotlin.math.ceil

/**
 * 删除链表的中间节点和a/b处的节点
 */
fun removeMidNode(head: ListNode?): ListNode? {
    if (head?.next == null) {
        // 链表为空，或只有一个节点的时候，直接返回
        return head
    }

    var pre = head
    var cur = head.next?.next

    while (cur?.next != null && cur.next?.next != null) {
        pre = pre?.next
        cur = cur.next?.next
    }

    pre?.next = pre?.next?.next

    return head
}

fun removeByRatio(headP: ListNode?, a: Int, b: Int): ListNode? {
    var head = headP

    if (a < 1 || a > b) {
        return head
    }

    var n = 0
    var cur = head

    while (cur != null) {
        cur = cur.next

        n++
    }

    n = ceil(((a * n) / b).toDouble()).toInt()

    if (n == 1) {
        // 删除第一个节点
        head = head!!.next
    }

    if (n > 1) {
        // 删除第2个到最后一个节点直接的某个节点
        cur = head

        // 遍历结束后，cur指向要删除节点的前一个节点
        while (--n != 1) {
            cur = cur?.next
        }

        cur?.next = cur?.next?.next
    }

    return head
}

fun main() {
    val head1 = ListNode(1)
    head1.next = ListNode(3)
    head1.next!!.next = ListNode(5)
    head1.next!!.next!!.next = ListNode(7)
    head1.next!!.next!!.next!!.next = ListNode(9)

    head1.printLinkedList()
    val removeMidNode = removeMidNode(head1)
    removeMidNode?.printLinkedList()

    val removeByRatio = removeByRatio(head1, 1, 4)
    removeByRatio.printLinkedList()
}