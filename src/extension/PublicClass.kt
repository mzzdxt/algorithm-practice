package extension

class TreeNode(val value: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class ListNode(val value: Int) {
    var next: ListNode? = null
}

class DoubleNode(val value: Int) {
    var pre: DoubleNode? = null
    var next: DoubleNode? = null
}