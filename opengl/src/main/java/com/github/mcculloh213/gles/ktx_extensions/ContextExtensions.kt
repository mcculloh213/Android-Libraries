@file:JvmName("ContextUtils")
package com.github.mcculloh213.gles.ktx_extensions

import android.app.ActivityManager
import android.content.Context

fun Context.detectOpenGlVersion(): Int {
    return getSystemService<ActivityManager>(ActivityManager::class.java)
        ?.deviceConfigurationInfo
        ?.reqGlEsVersion ?: 0x00000
}