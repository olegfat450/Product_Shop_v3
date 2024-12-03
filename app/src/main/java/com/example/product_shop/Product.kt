package com.example.product_shop

import android.content.Context
import android.content.Intent
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
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.net.toUri
import java.io.Serializable

class Product(val name:String?, val price:String?, val contextpr:String?, val image: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(price)
        parcel.writeString(contextpr)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}


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

             if ( product?.image != "null") image?.setImageURI(product?.image?.toUri()) else image?.setImageResource(R.drawable.qw)

             name?.text = " Товар: ${(product?.name)}"

              if ((product?.price)?.isEmpty() == true) { price?.text = " Цена:   Не установлено" } else price?.text = " Цена: ${product?.price}"
                 return view!! }
}

