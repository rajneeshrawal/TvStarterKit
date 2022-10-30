package com.rr.tvstarterkit.network


import okhttp3.Interceptor
import kotlin.Throws
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthorizationHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        //Adding common headers
        val requestBuilder: Request.Builder = original.newBuilder()
            .header(HttpConstant.ACCEPT, HttpConstant.APPLICATION_JSON)
            .header(HttpConstant.CONTENT_TYPE, HttpConstant.APPLICATION_JSON)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }
}