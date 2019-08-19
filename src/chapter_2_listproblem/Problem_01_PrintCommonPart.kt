package chapter_2_listproblem

/**
 * 打印两个有序链表的公共部分
 */
class Node(val value: Int) {
    var next: Node? = null
}

fun printCommonPart(list1: Node?, list2: Node?) {
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

fun printLinkedList(node: Node?) {
    print("Linked list: ")

    var nodeCopy = node

    while (nodeCopy != null) {
        print("${nodeCopy.value} ")

        nodeCopy = nodeCopy.next
    }

    println()
}

fun main() {
    val node1 = Node(1)
    node1.next = Node(3)
    node1.next!!.next = Node(5)
    node1.next!!.next!!.next = Node(7)
    node1.next!!.next!!.next!!.next = Node(9)

    val node2 = Node(1)
    node2.next = Node(2)
    node2.next!!.next = Node(3)
    node2.next!!.next!!.next = Node(4)
    node2.next!!.next!!.next!!.next = Node(5)

    printLinkedList(node1)
    printLinkedList(node2)
    printCommonPart(node1, node2)
}