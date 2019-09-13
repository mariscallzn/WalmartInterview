package com.andymariscal.walmartproducts.di

import com.andymariscal.walmartproducts.MainApplication
import com.andymariscal.walmartproducts.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        OkHttpClientModule::class,
        RetrofitModule::class,
        ViewModelModule::class,
        DataModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance mainApplication: MainApplication): AppComponent
    }
}