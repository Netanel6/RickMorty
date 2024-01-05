package com.netanel.rickmorty.domain

/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
open class DefaultRestError(
    val error: String? = null,
    val stack: String? = null,
    val result: String? = null,
) {

    open fun isErrorType(): Boolean {
        return error != null || stack != null && result != null
    }

    override fun toString(): String {
        return "DefaultRestError(error=$error, stack=$stack, result=$result)"
    }
}