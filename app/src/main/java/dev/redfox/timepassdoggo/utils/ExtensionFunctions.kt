package dev.redfox.timepassdoggo.utils

import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE

class ExtensionFunctions(
    private val defaultInterval: Long = 1000L,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {
    private var lastClickTime: Long = 1000L
    override fun onClick(view: View) {
        val timeElapsed = System.currentTimeMillis() - lastClickTime
        if (timeElapsed < defaultInterval) {
            return
        }
        lastClickTime = System.currentTimeMillis()
        onSafeClick.invoke(view)
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = ExtensionFunctions {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

fun View.show() {
    VISIBLE.also { visibility = it }
}

fun View.hide() {
    visibility = GONE
}

fun View.disappear() {
    visibility = INVISIBLE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}