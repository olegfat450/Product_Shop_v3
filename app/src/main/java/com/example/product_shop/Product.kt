package com.example.product_shop

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable

class Product(val name:String?, val price:String?, val contextpr:String?, val image: Drawable)



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

             image?.setImageDrawable(product?.image)

             name?.text = " Товар: ${(product?.name)}"

            if ((product?.price)?.isEmpty() == true) { price?.text = " Цена:   Не установлено" } else price?.text = " Цена: ${product?.price}"
                 return view!! }
}


