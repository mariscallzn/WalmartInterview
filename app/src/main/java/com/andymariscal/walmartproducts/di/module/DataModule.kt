package com.andymariscal.walmartproducts.di.module

import com.andymariscal.walmartproducts.data.ApiProductsDataSource
import com.andymariscal.walmartproducts.data.DefaultProductsRepository
import com.andymariscal.walmartproducts.data.ProductsDataSource
import com.andymariscal.walmartproducts.data.ProductsRepository
import com.andymariscal.walmartproducts.network.WalmartApi
import com.andymariscal.walmartproducts.util.NetworkUtils
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesProductsDataSource(
        api: WalmartApi,
        networkUtils: NetworkUtils
    ): ProductsDataSource =
        ApiProductsDataSource(api, networkUtils)

    @Provides
    fun providesProductsListRepository(
        dataSource: ProductsDataSource
    ): ProductsRepository = DefaultProductsRepository(dataSource)
}