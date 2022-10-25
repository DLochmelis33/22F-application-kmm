package me.dl33

interface Stack<T> : Collection<T> {
    fun push(elem: T)
    fun pop(): T?
}
