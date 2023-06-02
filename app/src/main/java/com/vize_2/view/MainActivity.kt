package com.vize_2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.vize_2.R
import com.vize_2.adapter.MyAdapter
import com.vize_2.adapter.OrdersAdapter
import com.vize_2.config.ApiClient
import com.vize_2.databinding.ActivityMainBinding
import com.vize_2.model.DummyProducts
import com.vize_2.model.Product
import com.vize_2.model.Productt
import com.vize_2.model.UserCarts
import com.vize_2.service.CartService
import com.vize_2.service.ProductService
import retrofit2.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var productService:ProductService
    lateinit var cartService: CartService
    lateinit var list:List<Productt>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //cartService=ApiClient.getClient().create(CartService::class.java)

        productService = ApiClient.getClient().create(ProductService::class.java)
        productService.getProducts("100").enqueue(object :Callback<DummyProducts>{
            override fun onResponse(call: Call<DummyProducts>, response: Response<DummyProducts>) {
                val datas = response.body()?.products
                list= datas!!
                datas?.let {
                    val myAdapter = MyAdapter(this@MainActivity, itemList =datas)
                    myAdapter.notifyDataSetChanged()
                    binding.listViewProducts.adapter=myAdapter
                    binding.listViewProducts.setOnItemClickListener { adapterView, view, i, l ->

                        var intent = Intent(this@MainActivity,DetailActivity::class.java)
                        intent.putExtra("data",datas.get(i).id.toString())
                        startActivity(intent)

                        true
                    }
                }
            }

            override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                t.printStackTrace()
            }

        }

        )
    }

    override fun onStart() {




        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.carts_menu,menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId==R.id.siparisler){
            //intent.getStringExtra("data1")?.let {

            binding.listViewProducts.visibility=View.GONE
            cartService = ApiClient.getClient().create(CartService::class.java)
            cartService.getUserCarts(1).enqueue(object : Callback<UserCarts>{
                override fun onResponse(call: Call<UserCarts>, response: Response<UserCarts>) {
                    response.body()?.let {
                        val datalist = response.body()!!.carts[0].products
                        val adapter =OrdersAdapter(this@MainActivity, datalist)
                            //ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, datas)
                        binding.listViewProducts.adapter = adapter
                        binding.listViewProducts.visibility = View.VISIBLE
                    }
                    }

                override fun onFailure(call: Call<UserCarts>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })

        }
        return super.onOptionsItemSelected(item)
    }
}