package com.vize_2.model

data class DummyProducts(
    val products: List<Productt>,
    val total: Long,
    val skip: Long,
    val limit: Long
)

data class Productt (
    val id: Long,
    val title: String,
    val description: String,
    val price: Long,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Long,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)


