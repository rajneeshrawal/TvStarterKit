package com.rr.tvstarterkit.di.module

import com.google.gson.Gson
import com.rr.tvstarterkit.network.AuthorizationHeaderInterceptor
import com.rr.tvstarterkit.network.HttpConstant
import com.rr.tvstarterkit.network.IAppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, (10 * 1024 * 1024).toLong()) //10MB Cache
    }

    @Provides
    @Singleton
    fun cacheFile(): File {
        return File("okhttp_cache")
    }


    @Provides
    @Singleton
    fun okHttpClient(
        cache: Cache,
        authorizationHeaderInterceptor: AuthorizationHeaderInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationHeaderInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            //.cache(cache)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        rxJava2CallAdapterFactory:RxJava2CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(HttpConstant.BASE_URL) //this is default base url
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideAuthorizationHeaderInterceptor(): AuthorizationHeaderInterceptor {
        return AuthorizationHeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IAppService =
        retrofit.create(IAppService::class.java)


}