package com.example.endlessproject

import com.example.endlessproject.tools.foundDesignatedPair
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExtensionTest {
    @Test
    fun addition_isCorrect() {
        val list:MutableList<Int> = mutableListOf(60,1960)
        val pair = list.foundDesignatedPair()
        val expected = pair.second + pair.first
        assertEquals(expected ,2020)

    }


}