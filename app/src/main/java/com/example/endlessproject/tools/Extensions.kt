package com.example.endlessproject.tools

/**
function will find to pair of number for exp (a,b) which a+b = sum :2020
 */
fun MutableList<Int>.foundDesignatedPair(sum: Int = 2020): Pair<Int, Int> {
    sort()
    forEach { item ->
        val tempVar = find {
            it == sum - item
        }
        if (tempVar != null) {
            return Pair(item, tempVar)
        }
    }
    return Pair(0, 0)
}
