package com.example.darshakrgbpracticaltest.utils.extensions

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.startIntent(key: String, value: Int, destinationClass: Class<*>) {
    Intent(this, destinationClass).apply {
        putExtra(key, value)
    }.also { startActivity(it) }
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}