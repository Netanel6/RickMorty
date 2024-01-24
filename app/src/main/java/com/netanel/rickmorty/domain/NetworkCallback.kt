package com.netanel.rickmorty.domain

import com.netanel.rickmorty.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by netanelamar on 05/01/2024
 * NetanelCA2@gmail.com
 */
// Abstract class representing a generic network callback for handling Retrofit responses.
abstract class NetworkCallback<T : DefaultRestError?> : Callback<T?> {

    // Handles the onResponse callback from Retrofit.
    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        // Check if the response is successful, if yes, invoke the onSuccess method.
        if (isResponseOK(response)) {
            onSuccess(response.body())
        } else {
            // If response is not successful, log information and invoke onFailure with a DefaultRestError.
            Logger.info("Netanel", "onResponse: $response")
            onFailure(DefaultRestError(error = response.raw().code.toString(), stack = response.raw().message, result = response.message().toString()))
        }
    }

    // Handles the onFailure callback from Retrofit, invoking onFailure with a default error.
    override fun onFailure(call: Call<T?>, throwable: Throwable) {
        onFailure(DefaultRestError(error = throwable.message, stack = throwable.cause?.message, throwable.stackTraceToString()))
    }

    // Abstract method to be implemented by subclasses for handling successful responses.
    abstract fun onSuccess(result: T?)

    // Abstract method to be implemented by subclasses for handling error responses.
    abstract fun onFailure(error: DefaultRestError?)

    // Companion object with utility methods and constants.
    companion object {
        const val TAG = "DefNetworkClbk"

        // Checks if the response is successful and not an error type.
        fun isResponseOK(response: Response<out DefaultRestError?>?): Boolean {
            return response?.body() != null && response.body()?.isErrorType() == false
        }
    }
}