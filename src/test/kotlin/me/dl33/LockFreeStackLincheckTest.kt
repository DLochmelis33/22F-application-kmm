package me.dl33

import org.jetbrains.kotlinx.lincheck.annotations.Operation
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import kotlin.test.Test

class LockFreeStackLincheckTest {

    private val stack: Stack<Int> = LockFreeStack()

    @Operation
    fun push(x: Int) = stack.push(x)

    @Operation
    fun pop() = stack.pop()

    @Operation
    fun size() = stack.size

    @Test
    fun stressTest() = StressOptions().check(this::class)
}