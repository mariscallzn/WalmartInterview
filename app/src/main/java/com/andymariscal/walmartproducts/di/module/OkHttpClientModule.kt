package com.andymariscal.walmartproducts.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class OkHttpClientModule {

    @Singleton
    @Provides
    fun okHttpClient(cache: Cache, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun cache(cacheFile: File): Cache = Cache(cacheFile, 10 * 1000 * 1000)// 10 MB

    @Provides
    fun file(context: Context): File = File(context.cacheDir, "HttpCache").apply {
        mkdirs()
    }
}