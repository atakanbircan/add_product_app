package com.vize_2.service


import com.vize_2.model.*
import retrofit2.Call

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CartService {
    @POST("carts/add")
    fun addCart(@Body cart: Cart): Cart

    @GET("carts/{cartId}")
    fun singleCart( @Path("cartId") id:Int ) : NewCart

    @GET("carts/user/{userId}")
    fun getUserCarts(@Path("userId") userId: Int): Call<UserCarts>
}