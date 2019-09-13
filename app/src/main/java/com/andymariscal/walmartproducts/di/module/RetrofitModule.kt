package com.andymariscal.walmartproducts.di.module

import com.andymariscal.walmartproducts.BuildConfig
import com.andymariscal.walmartproducts.network.WalmartApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    fun walmartApi(retrofit: Retrofit): WalmartApi = retrofit.create(WalmartApi::class.java)

    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BuildConfig.URL_BASE)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    fun rxAdapter(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    fun gson(): Gson = GsonBuilder().create()

}