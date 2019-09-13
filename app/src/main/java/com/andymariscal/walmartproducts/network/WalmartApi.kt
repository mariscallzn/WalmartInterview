package com.andymariscal.walmartproducts.network

import com.andymariscal.walmartproducts.model.ProductsList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WalmartApi {

    @GET("/walmartproducts/{pageNumber}/{pageSize}")
    fun getProductsList(
        @Path("pageNumber") pageNumber: Int,
        @Path("pageSize") pageSize: Int
    ): Observable<ProductsList>
}