package com.vize_2.service

import com.vize_2.model.DummyProducts
import com.vize_2.model.Product
import com.vize_2.model.Productt
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    fun getProducts(@Query("limit") limit: String, ) : Call<DummyProducts>

    @GET("products/{id}")
    fun singleProduct( @Path("id") id:String ) : Call<Productt>
}