package extension

fun ListNode?.printLinkedList() {
    print("Linked list: ")

    var head = this

    while (head != null) {
        print("${head.value} ")

        head = head.next
    }

    println()
}

fun DoubleNode?.printDoubleLinkedList() {
    var head = this

    println("Double Linked List:")

    var end: DoubleNode? = null

    while (head != null) {
        print("${head.value} ")
        end = head
        head = head.next
    }

    print("| ")

    while (end != null) {
        print("${end.value} ")
        end = end.pre
    }

    println()
}