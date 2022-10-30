package com.rr.tvstarterkit.network;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationHeaderInterceptor implements Interceptor {


    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header(HttpConstant.ACCEPT, HttpConstant.APPLICATION_JSON)
                .header(HttpConstant.CONTENT_TYPE, HttpConstant.APPLICATION_JSON);


        Request request = requestBuilder.build();

        return chain.proceed(request);
    }


}
