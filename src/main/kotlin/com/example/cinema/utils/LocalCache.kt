package com.example.cinema.utils

object LocalCache {
    var map: Map<String, HashSet<Int>> = HashMap()

    fun get(key: String): Any? {
        return map[key]
    }

    fun put(key: String, value: HashSet<Int>) {
        map[key]?.addAll(value)
    }

    @Synchronized
    fun contains(key: String, value: HashSet<Int>): Boolean {
        val set = map[key]
        if (set != null) {
            value.forEach {
                if (set.contains(it)) {
                    return true
                }
            }
        }
        return false
    }

    fun remove(key: String, value: HashSet<Int>) {
        map[key]?.removeAll(value)
    }
}
