package com.vize_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.vize_2.R
import com.vize_2.model.Product
import com.vize_2.model.Productt

class OrdersAdapter(private val context: Context, private val itemList:List<Product>): BaseAdapter() {

    lateinit var txtCartTitle: TextView
    lateinit var txtCartDiscountedPrice: TextView
    lateinit var txtCartQuantity: TextView
    lateinit var txtCartPrice: TextView


    override fun getCount(): Int {
       return itemList.size
    }

    override fun getItem(p0: Int): Any {
        return itemList[p0]
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.orders_list_item,p2,false)

        txtCartTitle= view.findViewById(R.id.txtCartTitle)
        txtCartQuantity = view.findViewById(R.id.txtCartQuantity)
        txtCartPrice = view.findViewById(R.id.txtCartPrice)
        txtCartDiscountedPrice = view.findViewById(R.id.txtCartDiscountedPrice)


        txtCartTitle.text = itemList[p0].title
        txtCartQuantity.text = itemList[p0].quantity.toString()
        txtCartPrice.text = itemList[p0].price.toString()
        txtCartDiscountedPrice.text = itemList[p0].discountedPrice.toString()

        return view
    }
}