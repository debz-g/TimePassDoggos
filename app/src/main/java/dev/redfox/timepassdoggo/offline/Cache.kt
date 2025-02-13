package dev.redfox.timepassdoggo.offline

interface Cache<K, V> {

    fun put(key: K, value: V)

    fun get(key: K): V?

    fun getAllKeys(): List<K>

    fun getAllValues(): List<V>

    val currentSize: Int

    fun clearAll()

}