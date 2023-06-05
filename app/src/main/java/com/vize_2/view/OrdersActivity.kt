package com.vize_2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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

       /* cartService=ApiClient.getClient().create(CartService::class.java)
        val cart = Cart(
            userId = 1,
            products = listOf(
                CartProduct(id = 1, quantity = 1),
                CartProduct(id = 50, quantity = 2)
            )

        )
        val  response =cartService.addCart(cart)
        Log.d("postresponse",response.toString())
*/
        super.onStart()



        binding.txtTitleOrders.text="Orders"
        cartService = ApiClient.getClient().create(CartService::class.java)
        cartService.getUserCarts(1).enqueue(object : Callback<UserCarts>{
            override fun onResponse(call: Call<UserCarts>, response: Response<UserCarts>) {
                response.body()?.let {
                    val datalist = response.body()!!.carts[0].products
                    val adapter = OrdersAdapter(this@OrdersActivity, datalist)
                    //ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, datas)
                    binding.ordersListView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<UserCarts>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })




    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intentback=Intent(this@OrdersActivity,MainActivity::class.java)
        intentback.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intentback)
    }
}