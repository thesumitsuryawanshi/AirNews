package com.example.airnews.Model

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request =
            chain.request().newBuilder().addHeader("X-Api-Key", "11c6dba5e88744338808d830416b0b8f")
                .build()
        return chain.proceed(request)
    }

}


//&apiKey=11c6dba5e88744338808d830416b0b8f