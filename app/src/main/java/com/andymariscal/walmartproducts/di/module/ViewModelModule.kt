package com.andymariscal.walmartproducts.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: WalmartViewModelFactory):
            ViewModelProvider.Factory
}