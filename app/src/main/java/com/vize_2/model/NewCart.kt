package com.vize_2.model


data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val quantity: Int,
    val total: Int,
    val discountPercentage: Double,
    val discountedPrice: Int
)

data class NewCart(
    val id: Int,
    val products: List<Product>,
    val total: Int,
    val discountedTotal: Int,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)

data class UserCarts(
    val carts: List<NewCart>,
    val total: Int,
    val skip: Int,
    val limit: Int
)



data class CartProduct(val id: Int, val quantity: Int)

data class Cart(val userId: Int, val products: List<CartProduct>)

data class User(val id: Int, val name: String, val age: Int)
