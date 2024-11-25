package com.example.product_shop

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.toBitmap
import java.util.ArrayList

class Activity2 : AppCompatActivity() {

          private lateinit var toolbar: Toolbar
          private lateinit var imageTv: ImageView
          private lateinit var button: Button
          private lateinit var name: EditText
          private lateinit var price: EditText
          private lateinit var listTv:ListView
          private lateinit var contextpr: EditText

         var GALLARY = 111
              var listProduct: MutableList<Product> = mutableListOf()


           var s = 0

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)

        imageTv = findViewById(R.id.imageTv)
        button = findViewById(R.id.button)
        name = findViewById(R.id.nameText)
        price = findViewById(R.id.priceText)
        listTv = findViewById(R.id.textTv)
        contextpr = findViewById(R.id.contextTv)

        val adapter = ListAdapter(this,listProduct)
          listTv.adapter = adapter

        imageTv.setOnClickListener {
            val photo = Intent(Intent.ACTION_PICK)
            photo.type = "image/*"
            startActivityForResult(photo, GALLARY) }

        button.setOnClickListener{
               if (name.text.isEmpty()) return@setOnClickListener

                      listProduct += listOf(Product(name.text.toString(),price.text.toString(),contextpr.text.toString(),imageTv.drawable))

              adapter.notifyDataSetChanged()
              name.text.clear()
              price.text.clear()
              imageTv.setImageResource(R.drawable.qw)
              contextpr.text.clear()

        }

           listTv.onItemClickListener = selectItem(this,adapter)



        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        title = "   Добавление продуктов" }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
           menuInflater.inflate(R.menu.menuexit,menu)
        return super.onCreateOptionsMenu(menu) }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        AlertDialog.Builder(this).setTitle("Выход из программы").setMessage("Действительно выйти?")
                       .setPositiveButton("Да") { s,v -> finishAffinity()}
                       .setNegativeButton("Нет") {s,v -> s.cancel()}.create().show()
        return super.onOptionsItemSelected(item) }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent? ) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            GALLARY -> if (resultCode === RESULT_OK) {

                val imageUrl = data?.data
                imageTv.setImageURI(imageUrl) } } }



    fun selectItem(context: Context, adapter: ArrayAdapter<Product>) =


        AdapterView.OnItemClickListener{ s, v, position, id ->
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Редактировать").setCancelable(true)
                .setNegativeButton("Удалить") { d,w ->   Toast.makeText(applicationContext,"Продукт ${listProduct[position].name} удален",
                    Toast.LENGTH_LONG).show(); adapter.remove(adapter.getItem(position)) }
                .setPositiveButton("Посмотреть") { d,w -> activity3( position)}
                .setNeutralButton("Редактировать") { d,w -> name.setText(listProduct[position].name);price.setText(listProduct[position].price);
                                                                   contextpr.setText(listProduct[position].contextpr)
                                                                  imageTv.setImageDrawable(listProduct[position].image)
                                                                  adapter.remove(adapter.getItem(position)) }.create(); builder.show() }
    @SuppressLint("SuspiciousIndentation")
    fun activity3(position: Int){

                 imageTv.setImageDrawable(listProduct[position].image)
                  imageTv.buildDrawingCache()
          val b = imageTv.getDrawingCache()

          val intent = Intent(this,Activity3::class.java)

        intent.putExtra("image",b)
        intent.putExtra("name",listProduct[position].name)
        intent.putExtra("price",listProduct[position].price)
        intent.putExtra("context",listProduct[position].contextpr)

            startActivity(intent)

              imageTv.setImageResource(R.drawable.qw)

    }

}

