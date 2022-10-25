package me.dl33

import java.util.concurrent.atomic.AtomicReference

class LockFreeStack<T> : Stack<T> {

    private inner class Node(val element: T, val next: Node?, val size: Int = 1 + (next?.size ?: 0))

    private val headRef = AtomicReference<Node>(null)

    /**
     * Pushes an element onto the stack.
     */
    override fun push(elem: T) {
        do {
            val currentHead = headRef.get()
            val newNode = Node(elem, currentHead)
        } while (!headRef.compareAndSet(currentHead, newNode))
    }

    /**
     * Removes top element from the stack and returns it, or returns null if the stack is empty.
     */
    override fun pop(): T? {
        var result: T?
        do {
            val currentHead: Node? = headRef.get()
            result = currentHead?.element
            val prevNode = currentHead?.next
        } while (!headRef.compareAndSet(currentHead, prevNode))
        return result
    }

    override val size get() = headRef.get()?.size ?: 0

    override fun contains(element: T) = iterator().asSequence().contains(element)

    override fun containsAll(elements: Collection<T>) = iterator().asSequence().toSet().containsAll(elements)

    override fun isEmpty() = size == 0

    /**
     * Traverses the stack top-to-bottom. Does NOT take a global snapshot.
     */
    override fun iterator() = object : Iterator<T> {
        private var currentNode: LockFreeStack<T>.Node? = headRef.get()
        override fun next() = currentNode!!.element!!.also { currentNode = currentNode!!.next }
        override fun hasNext() = currentNode != null
    }
}
