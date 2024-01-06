package com.netanel.rickmorty.domain

import com.netanel.rickmorty.utils.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Netanel Amar on 05/01/2024.
 * NetanelCA2@gmail.com
 */
// Singleton object responsible for creating and managing the Retrofit instance for API calls.
object RetrofitInstance {

    // Logger tag for debugging purposes.
    private val TAG = this::class.simpleName.toString()

    // OkHttpClient builder instance.
    private var okHttpClient: OkHttpClient.Builder? = null

    // Retrofit instance.
    private var retrofit: Retrofit? = null

    // Initialization block to set up the OkHttpClient builder.
    init {
        okHttpClient = OkHttpClient.Builder()
    }

    // Creates and returns an instance of the specified service interface using the Retrofit builder.
    fun <T> create(service: Class<T>): T {
        Logger.info(TAG, "create: true")
        return build().create(service)
    }

    // Builds and configures the Retrofit instance with necessary settings.
    private fun build(): Retrofit {
        // Add an interceptor to the OkHttpClient for request modification.
        okHttpClient?.interceptors()?.add(Interceptor { chain ->
            val request: Request.Builder = chain.request().newBuilder()
            chain.proceed(request.build())
        })

        // Add an HTTP logging interceptor for debugging purposes.
        okHttpClient?.interceptors()
            ?.add(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        // Build OkHttpClient and configure Retrofit.
        val client: OkHttpClient? = okHttpClient?.build()
        val builder: Retrofit.Builder? = client?.let {
            Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .client(it)
                .addConverterFactory(GsonConverterFactory.create())
        }

        // Build and store the Retrofit instance.
        retrofit = builder?.build()
        return retrofit as Retrofit
    }
}
