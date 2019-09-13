package com.andymariscal.walmartproducts.di.module

import com.andymariscal.walmartproducts.di.ActivityScoped
import com.andymariscal.walmartproducts.ui.MainActivity
import com.andymariscal.walmartproducts.ui.home.HomeModule
import com.andymariscal.walmartproducts.ui.productdetail.ProductDetailModule
import com.andymariscal.walmartproducts.ui.products.ProductsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            HomeModule::class,
            ProductsModule::class,
            ProductDetailModule::class]
    )
    internal abstract fun mainActivity(): MainActivity
}