package com.netanel.rickmorty.utils

import android.util.Log


/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
object Logger {

    fun info(tag: String = "info", message: String = "message") {
        Log.i(tag, message)
    }

}