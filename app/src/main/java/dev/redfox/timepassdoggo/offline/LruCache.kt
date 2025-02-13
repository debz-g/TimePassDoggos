package dev.redfox.timepassdoggo.offline

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.redfox.timepassdoggo.utils.base64ToBitmap
import dev.redfox.timepassdoggo.utils.bitmapToBase64
import java.io.ByteArrayOutputStream

class LruCache<K, V>(
    private val context: Context,
    private val maxSize: Int
) : Cache<K, V> {

    private val sharedPreferences = context.getSharedPreferences("lru_cache", Context.MODE_PRIVATE)
    private val cache: MutableMap<K, V> = LinkedHashMap(16, 0.75f, true)

    init {
        loadFromPreferences()
    }

    override fun put(key: K, value: V) {
        if (currentSize >= maxSize) {
            cache.remove(cache.keys.first()) // Remove oldest (LRU)
        }
        cache[key] = value
        saveToPreferences()
    }

    override fun get(key: K): V? {
        return cache[key]?.also {
            cache.remove(key) // Move accessed item to the end
            cache[key] = it
            saveToPreferences()
        }
    }

    override fun getAllKeys(): List<K> = cache.keys.toList()

    override fun getAllValues(): List<V> = cache.values.toList()

    override val currentSize: Int
        get() = cache.size

    override fun clearAll() {
        cache.clear()
        sharedPreferences.edit().clear().apply()
    }

    private fun saveToPreferences() {
        val editor = sharedPreferences.edit()
        val gson = Gson()

        // Convert Bitmap to Base64 for serialization
        val serializedKeys = gson.toJson(cache.keys)
        val serializedValues = gson.toJson(cache.values.map {
            if (it is Bitmap) bitmapToBase64(it) else it
        })

        editor.putString("cache_keys", serializedKeys)
        editor.putString("cache_values", serializedValues)
        editor.apply()
    }

    private fun loadFromPreferences() {
        val gson = Gson()
        val keysJson = sharedPreferences.getString("cache_keys", null)
        val valuesJson = sharedPreferences.getString("cache_values", null)

        if (keysJson != null && valuesJson != null) {
            val tokenKeyType = object : TypeToken<List<K>>() {}.type
            val tokenValueType = object : TypeToken<List<V>>() {}.type
            val keyList: List<K> = gson.fromJson(keysJson, tokenKeyType)
            val valueList: List<V> = gson.fromJson(valuesJson, tokenValueType)

            keyList.zip(valueList).forEach { (key, value) ->
                if (value is String) {
                    cache[key] = base64ToBitmap(value) as V
                } else {
                    cache[key] = value
                }
            }
        }
    }
}