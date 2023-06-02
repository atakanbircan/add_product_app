package com.vize_2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vize_2.adapter.OrdersAdapter
import com.vize_2.config.ApiClient
import com.vize_2.databinding.ActivityOrdersBinding
import com.vize_2.model.*
import com.vize_2.service.CartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class OrdersActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrdersBinding
    lateinit var cartService: CartService
    lateinit var list:List<NewCart>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOrdersBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


    override fun onStart() {

        cartService=ApiClient.getClient().create(CartService::class.java)
        val cart = Cart(
            userId = 1,
            products = listOf(
                CartProduct(id = 1, quantity = 1),
                CartProduct(id = 50, quantity = 2)
            )

        )
        val  response =cartService.addCart(cart)
        Log.d("postresponse",response.toString())

        super.onStart()
    }
}