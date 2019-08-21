package chapter_2_listproblem

import extension.DoubleNode
import extension.ListNode
import extension.printDoubleLinkedList
import extension.printLinkedList

/**
 * 在单链表和双链表中删除倒数第K个节点
 */

/**
 * 单链表
 */
fun removeLastKthNode(headP: ListNode?, lastKthP: Int): ListNode? {
    var lastKth = lastKthP
    var head = headP

    if (head == null || lastKth < 1) {
        return head
    }

    var cur = head

    while (cur != null) {
        lastKth--
        cur = cur.next
    }

    if (lastKth == 0) {
        head = head.next
    } else if (lastKth < 0) {
        cur = head
        while (++lastKth != 0) {
            cur = cur!!.next
        }
        cur!!.next = cur.next!!.next
    }

    return head
}

/**
 * 双链表
 */
fun removeLastKthNode(headP: DoubleNode?, lastKthP: Int): DoubleNode? {
    var lastKth = lastKthP
    var head = headP

    if (head == null || lastKth < 1) {
        return head
    }

    var cur = head

    while (cur != null) {
        lastKth--
        cur = cur.next
    }

    if (lastKth == 0) {
        head = head.next
        head!!.pre = null
    } else if (lastKth < 0) {
        cur = head
        while (++lastKth != 0) {
            cur = cur!!.next
        }

        val newNext = cur!!.next!!.next
        cur.next = newNext

        if (newNext != null) {
            newNext.pre = cur
        }
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
    val removedHead1 = removeLastKthNode(head1, 2)
    removedHead1.printLinkedList()

    val head2 = DoubleNode(1)

    head2.next = DoubleNode(2)
    head2.next!!.pre = head2

    head2.next!!.next = DoubleNode(3)
    head2.next!!.next!!.pre = head2.next

    head2.next!!.next!!.next = DoubleNode(4)
    head2.next!!.next!!.next!!.pre = head2.next!!.next

    head2.next!!.next!!.next!!.next = DoubleNode(5)
    head2.next!!.next!!.next!!.next!!.pre = head2.next!!.next!!.next

    head2.printDoubleLinkedList()
    val removedHead2 = removeLastKthNode(head2, 3)
    removedHead2.printDoubleLinkedList()
}