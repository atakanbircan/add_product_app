package com.vize_2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.vize_2.config.ApiClient
import com.vize_2.databinding.ActivityDetailBinding
import com.vize_2.model.*
import com.vize_2.service.CartService
import com.vize_2.service.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var productService:ProductService
    lateinit var cartService:CartService
    lateinit var mutableList: MutableList<CartProduct>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getStringExtra("data")?.let {
            var id = it

            cartService = ApiClient.getClient().create(CartService::class.java)
            productService = ApiClient.getClient().create(ProductService::class.java)

            productService.singleProduct(id).enqueue(object : Callback<Productt> {
                override fun onResponse(call: Call<Productt>, response: Response<Productt>) {
                    val count = response.body()
                    count?.let {
                        binding.txtDetailPageTitle.text = count.title
                        binding.txtDetailPageDescription.text = count.description
                        binding.txtDetailPageRating.text = count.description
                        binding.txtDetailPagePrice.text = count.price.toString()

                        Glide.with(this@DetailActivity).load(count.images[1])
                            .into(binding.imgDetail)
                    }
                }

                override fun onFailure(call: Call<Productt>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })

            binding.btnSepeteEkle.setOnClickListener {


                val cart = Cart(1,listOf(CartProduct(id.toInt(),1)))
                cartService.addCart(cart)

                var intent =
                    Intent(this@DetailActivity, MainActivity::class.java)

                startActivity(intent)
            }


        }


















































        /* val product = productService.singleProduct(id).enqueue(object : Callback<Productt>{


               // override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    override fun onResponse(call: Call<Productt>, response: Response<Productt>) {
                        val count = response.body()
                        count?.let {
                            binding.txtDetailPageTitle.text= count.title
                            binding.txtDetailPageDescription.text=count.description
                            binding.txtDetailPageRating.text=count.description
                            binding.txtDetailPagePrice.text=count.price.toString()

                            Glide.with(this@DetailActivity).load(count.images[1])
                                .into(binding.imgDetail)
                            binding.btnSepeteEkle.setOnClickListener {
                                val cartProduct= CartProduct(count.id.toInt(),1)
                                val cart = Cart(1,cartService.getUserCarts(cartProduct.id).carts)
                                cartService.addCart(cart).enqueue(object :Callback<Product>{
                                    override fun onResponse(
                                        call: Call<Product>,
                                        response: Response<Product>
                                    ) {
                                        val Product = response.body()


                                        var intent =
                                            Intent(this@DetailActivity, MainActivity::class.java)

                                        startActivity(intent)
                                    }


                                    override fun onFailure(call: Call<Product>, t: Throwable) {
                                        t.printStackTrace()
                                    }




                                })
                                // var arr= arrayListOf<Productss>()
                                // arr.add(productss)
                                //val cart = Cart(1, productss = arr)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Product>, t: Throwable) {
                        t.printStackTrace()
                    }

            })
*/


    }
}