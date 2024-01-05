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
object RetrofitInstance {
    private val TAG = this::class.simpleName.toString()
    private var okHttpClient: OkHttpClient.Builder? = null
    private var retrofit: Retrofit? = null

    init {
        okHttpClient = OkHttpClient.Builder()
    }

    fun <T> create(service: Class<T>): T {
        Logger.info(TAG, "create: true")
        return build().create(service)
    }

    private fun build(): Retrofit {
        okHttpClient?.interceptors()?.add(Interceptor { chain ->
            val request: Request.Builder = chain.request().newBuilder()
            chain.proceed(request.build())
        })

        okHttpClient?.interceptors()
            ?.add(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        val client: OkHttpClient? = okHttpClient?.build()
        val builder: Retrofit.Builder? = client?.let {
            Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .client(it)
                .addConverterFactory(GsonConverterFactory.create())
        }

        retrofit = builder?.build()
        return retrofit as Retrofit
    }
}