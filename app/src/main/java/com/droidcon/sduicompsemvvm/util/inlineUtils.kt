package com.droidcon.sduicompsemvvm.util

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> runOnIO(crossinline block: suspend () -> T): T {
    return withContext(Dispatchers.IO) {
        block.invoke()
    }
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}