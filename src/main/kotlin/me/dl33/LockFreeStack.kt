package me.dl33

import kotlinx.atomicfu.atomic
import kotlinx.atomicfu.getAndUpdate
import kotlinx.atomicfu.update

class LockFreeStack<T> : Stack<T> {

    private inner class Node(val element: T, val next: Node?, val size: Int = 1 + (next?.size ?: 0))

    private val headRef = atomic<Node?>(null)

    /**
     * Pushes an element onto the stack.
     */
    override fun push(elem: T) {
        headRef.update { Node(elem, it) }
    }

    /**
     * Removes top element from the stack and returns it, or returns null if the stack is empty.
     */
    override fun pop(): T? {
        return headRef.getAndUpdate { it?.next }?.element
    }

    override val size get() = headRef.value?.size ?: 0

    override fun contains(element: T) = iterator().asSequence().contains(element)

    override fun containsAll(elements: Collection<T>) = iterator().asSequence().toSet().containsAll(elements)

    override fun isEmpty() = size == 0

    /**
     * Traverses the stack top-to-bottom. Does NOT take a consistent snapshot.
     */
    override fun iterator() = object : Iterator<T> {
        private var currentNode: LockFreeStack<T>.Node? = headRef.value
        override fun next() = currentNode!!.element!!.also { currentNode = currentNode!!.next }
        override fun hasNext() = currentNode != null
    }
}
