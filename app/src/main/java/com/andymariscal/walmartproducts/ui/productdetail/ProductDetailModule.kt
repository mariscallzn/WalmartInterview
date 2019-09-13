package com.andymariscal.walmartproducts.ui.productdetail

import androidx.lifecycle.ViewModel
import com.andymariscal.walmartproducts.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ProductDetailModule {

    @ContributesAndroidInjector
    abstract fun contributesProductDetailsFragment(): ProductDetailFragment


    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel::class)
    abstract fun bindProductDetailViewModel(productDetailViewModel: ProductDetailViewModel): ViewModel

}
