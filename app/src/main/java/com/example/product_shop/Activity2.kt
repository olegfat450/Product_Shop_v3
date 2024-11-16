package com.example.product_shop

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import java.io.IOException

class Activity2 : AppCompatActivity() {

          private lateinit var toolbar: Toolbar
          private lateinit var imageTv: ImageView
          private lateinit var button: Button
          private lateinit var name: EditText
          private lateinit var price: EditText
          private lateinit var listTv:ListView

         var GALLARY = 111
         var bitmap: Bitmap? = null
              var listProduct: MutableList<Product> = mutableListOf()


           var s = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)

        imageTv = findViewById(R.id.imageTv)
        button = findViewById(R.id.button)
        name = findViewById(R.id.nameText)
        price = findViewById(R.id.priceText)
        listTv = findViewById(R.id.textTv)


        val adapter = ListAdapter(this,listProduct)
        listTv.adapter = adapter

        imageTv.setOnClickListener {
            val photo = Intent(Intent.ACTION_PICK)
            photo.type = "image/*"
            startActivityForResult(photo, GALLARY) }

        button.setOnClickListener{
               if (name.text.isEmpty()) return@setOnClickListener

            bitmap = (imageTv.drawable as BitmapDrawable).bitmap
                 listProduct += listOf(Product(name.text.toString(),price.text.toString(),bitmap))


               adapter.notifyDataSetChanged()
              name.text.clear()
              price.text.clear()
              imageTv.setImageResource(R.drawable.qw)
               bitmap = null }

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
                .setNegativeButton("Удалить") { d,w ->   Snackbar.make(v,"Продукт ${listProduct[position].name} удален",
                    Snackbar.LENGTH_LONG).show(); adapter.remove(adapter.getItem(position)) }
                .setPositiveButton("Редактировать") { d,w -> name.setText(listProduct[position].name);price.setText(listProduct[position].price)
                                                                  imageTv.setImageBitmap(listProduct[position].image);
                                                                   if (listProduct[position].image == null) imageTv.setImageResource(R.drawable.qw)
                                                                  adapter.remove(adapter.getItem(position))

                }.create()
            builder.show()


        }

}

