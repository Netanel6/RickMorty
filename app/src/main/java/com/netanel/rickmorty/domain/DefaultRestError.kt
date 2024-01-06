package com.netanel.rickmorty.domain

/**
 * Created by netanelamar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
// Represents a default REST error response with optional fields: error, stack, and result.
open class DefaultRestError(
    val error: String? = null,
    val stack: String? = null,
    val result: String? = null,
) {

    // Checks if the error instance represents an error type by examining its fields.
    open fun isErrorType(): Boolean {
        return error != null || (stack != null && result != null)
    }

    // Overrides the default toString() method to provide a readable representation of the error.
    override fun toString(): String {
        return "DefaultRestError(error=$error, stack=$stack, result=$result)"
    }
}