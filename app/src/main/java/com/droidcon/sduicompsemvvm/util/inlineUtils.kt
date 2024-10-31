package com.droidcon.sduicompsemvvm.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> runOnIO(crossinline block: suspend () -> T): T {
    return withContext(Dispatchers.IO) {
        block.invoke()
    }
}