package com.github.abhrp.presentation.test.factory

import java.util.*
import java.util.concurrent.ThreadLocalRandom

object DataFactory {
    fun randomString(): String = UUID.randomUUID().toString()
    fun randomInt(): Int = ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    fun randomBoolean(): Boolean = Math.random() < 0.5
    fun randomDouble(): Double = randomInt().toDouble()
    fun randomLong(): Long = randomInt().toLong()
    fun randomIntList(count: Int): List<Int> {
        val numbers = mutableListOf<Int>()
        repeat(count) {
            numbers.add(randomInt())
        }
        return numbers
    }
}