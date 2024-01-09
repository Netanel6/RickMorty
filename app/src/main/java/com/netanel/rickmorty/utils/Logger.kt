package com.netanel.rickmorty.utils

import android.util.Log
import com.netanel.rickmorty.BuildConfig


/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
object Logger {

    fun info(tag: String = "", message: String = "") {
        if (!BuildConfig.DEBUG) return
        Log.i(tag, message)
    }

}