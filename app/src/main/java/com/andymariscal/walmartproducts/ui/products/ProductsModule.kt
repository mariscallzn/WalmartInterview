package com.andymariscal.walmartproducts.ui.products

import androidx.lifecycle.ViewModel
import com.andymariscal.walmartproducts.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@Suppress("UNUSED")
internal abstract class ProductsModule {

    @ContributesAndroidInjector
    internal abstract fun contributeProductsFragment() : ProductsFragment

    @Binds
    @IntoMap
    @ViewModelKey(ProductsListViewModel::class)
    abstract fun bindProductsListViewModel(productsListViewModel: ProductsListViewModel):
            ViewModel

}