package com.vize_2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vize_2.R
import com.vize_2.model.Product
import com.vize_2.model.Productt
import kotlinx.coroutines.CompletableDeferred

class MyAdapter(private val context: Context,private val itemList:List<Productt>):BaseAdapter() {

    lateinit var txtTitle: TextView
    lateinit var imgProduct: ImageView
    lateinit var txtDescrition: TextView
    lateinit var txtPrice: TextView
    lateinit var txtRate: TextView

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): Any {
        return  itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val inflater :LayoutInflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.list_item,p2,false)

        txtTitle= view.findViewById(R.id.txtTitle)
        txtDescrition = view.findViewById(R.id.txtDescription)
        txtPrice = view.findViewById(R.id.txtPrice)
        txtRate = view.findViewById(R.id.txtRate)
        imgProduct = view.findViewById(R.id.imgProduct)

        txtTitle.text = itemList[position].title
        txtDescrition.text = itemList[position].description
        txtPrice.text = itemList[position].price.toString()
        txtRate.text = itemList[position].rating.toString()

        Glide.with(view).load(itemList[position].images[0]).into(imgProduct)

        return view
    }

}