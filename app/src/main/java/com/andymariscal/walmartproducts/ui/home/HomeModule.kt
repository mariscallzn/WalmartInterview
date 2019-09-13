package com.andymariscal.walmartproducts.ui.home

import androidx.lifecycle.ViewModel
import com.andymariscal.walmartproducts.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

}