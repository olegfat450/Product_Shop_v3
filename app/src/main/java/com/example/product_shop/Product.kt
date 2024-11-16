package com.example.product_shop

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

class Product(val name:String, val price:String, val image: Bitmap?) {}

class ListAdapter(context: Context,listProduct: MutableList<Product>): ArrayAdapter<Product> (context,R.layout.item,listProduct) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        val product = getItem(position)

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        }

        val name = view?.findViewById<TextView>(R.id.nameText_item)
        val price = view?.findViewById<TextView>(R.id.priceText_item)
        val image = view?.findViewById<ImageView>(R.id.imageTv_item)

               image?.setImageBitmap(product?.image)

            name?.text = " Товар: ${(product?.name)}"

            if ((product?.price)?.isEmpty() == true) { price?.text = " Цена:   Не установлено" } else price?.text = " Цена: ${product?.price}"
                 return view!! }
}


