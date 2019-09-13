package com.andymariscal.walmartproducts.di.module

import android.content.Context
import com.andymariscal.walmartproducts.MainApplication
import com.andymariscal.walmartproducts.util.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesContext(application: MainApplication): Context = application.applicationContext

    @Provides
    fun providesNetworkUtils(context: Context) = NetworkUtils(context)
}