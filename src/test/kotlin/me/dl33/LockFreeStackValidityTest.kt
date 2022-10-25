package me.dl33

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LockFreeStackValidityTest {

    @Test
    fun `basic test`() {
        val stack = LockFreeStack<Int>()
        assertTrue { stack.isEmpty() }
        assertEquals(null, stack.pop())
        stack.push(1)
        assertEquals(1, stack.size)
        assertEquals(1, stack.pop())
        assertEquals(0, stack.size)
        assertTrue { stack.isEmpty() }
        assertEquals(null, stack.pop())
    }

    @Test
    fun `FIFO order`() {
        val stack = LockFreeStack<Int>()
        for (x in listOf(1, 2, 3, 4, 5)) stack.push(x)
        val result = mutableListOf<Int>()
        while (stack.pop()?.also { result.add(it) } != null);
        assertEquals(listOf(5, 4, 3, 2, 1), result)
    }

    @Test
    fun `collection methods`() {
        val stack = LockFreeStack<Int>()
        for (x in listOf(1, 2, 3, 4, 5)) stack.push(x)

        assertEquals(listOf(5, 4, 3, 2, 1), stack.iterator().asSequence().toList())
        assertTrue { stack.contains(3) }
        assertFalse { stack.contains(0) }
        assertTrue { stack.containsAll(listOf(1, 5, 3)) }
        assertFalse { stack.containsAll(listOf(1, -1)) }
    }

}