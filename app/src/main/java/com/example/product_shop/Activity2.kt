package com.example.product_shop

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ComponentCaller
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
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
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import java.util.ArrayList

class Activity2 : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
          private lateinit var imageTv: ImageView
          private lateinit var button: Button
          private lateinit var name: EditText
          private lateinit var price: EditText
          private lateinit var listTv:ListView
          private lateinit var contextpr: EditText

          private lateinit var photoPickerLauncher: ActivityResultLauncher<Intent>
              var listProduct: MutableList<Product> = mutableListOf()
    var imageUri: Uri? = null
       // var prod: Product? = null

            var listtemp: List<Product> = listOf()

    @SuppressLint("WrongThread", "ResourceType", "SuspiciousIndentation")
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


        photoPickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK) { imageUri = result.data?.data
                 imageTv.setImageURI(imageUri)
            }


        }

        val adapter = ListAdapter(this,listProduct)
          listTv.adapter = adapter

        imageTv.setOnClickListener {

            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            photoPickerLauncher.launch(photoPickerIntent)
        }

        button.setOnClickListener{
                 if (name.text.isEmpty()) return@setOnClickListener

                       listProduct += listOf(Product(name.text.toString(),price.text.toString(),contextpr.text.toString(),imageUri.toString()))



              name.text.clear()
              price.text.clear()
              imageTv.setImageResource(R.drawable.qw)
              imageUri = null

              contextpr.text.clear() }
                    adapter.notifyDataSetChanged()




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



    fun selectItem(context: Context, adapter: ArrayAdapter<Product>) =
        AdapterView.OnItemClickListener{ s, v, position, id ->
            val builder = AlertDialog.Builder(context)

            builder.setTitle("Редактировать").setCancelable(true)
                .setNegativeButton("Удалить") { d,w ->   Toast.makeText(applicationContext,"Продукт ${listProduct[position].name} удален",
                    Toast.LENGTH_LONG).show(); adapter.remove(adapter.getItem(position)) }
                    .setPositiveButton("Посмотреть") { d,w -> activity3( position );adapter.remove(adapter.getItem(position));adapter.notifyDataSetChanged() }.create(); builder.show() }



//                .setNeutralButton("Редактировать") { d,w -> name.setText(listProduct[position].name);price.setText(listProduct[position].price);
//                                                                   contextpr.setText(listProduct[position].contextpr)
//                                                                  imageTv.setImageURI(listProduct[position].image?.toUri());
//                                                                  imageUri = listProduct[position].image?.toUri()
//                                                                  adapter.remove(adapter.getItem(position)) }.create(); builder.show() }
    @SuppressLint("SuspiciousIndentation")
    fun activity3(position: Int) {

    listtemp = listOf(Product(listProduct[position].name,
        listProduct[position].price,listProduct[position].contextpr,listProduct[position].image))

        val intent = Intent(this, Activity3::class.java)

        intent.putExtra(Product::class.java.simpleName, listProduct[position])

        startActivityForResult(intent, 111)
   }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111) {
            if (resultCode == RESULT_OK) {
          val prod = data?.extras?.getParcelable(Product::class.java.simpleName) as Product?

            name.setText("");
            listProduct += listOf(Product(prod?.name,prod?.price, prod?.contextpr,prod?.image))

            } else { name.setText("");listProduct += listtemp }
        }



        }
}



