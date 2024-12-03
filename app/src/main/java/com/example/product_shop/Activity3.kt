package com.example.product_shop

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class Activity3 : AppCompatActivity() {

      private lateinit var imageTv: ImageView
       private lateinit var nametext: EditText
       private lateinit var pricetext: EditText
       private lateinit var contexttext: EditText
       private lateinit var button1: Button
       private lateinit var button2: Button
       private lateinit var toolbar: Toolbar
    private lateinit var photoPickerLauncher: ActivityResultLauncher<Intent>


    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_3)

        imageTv = findViewById(R.id.imageTv)
        nametext = findViewById(R.id.nameText)
        pricetext = findViewById(R.id.priceText)
        contexttext = findViewById(R.id.contextText)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        toolbar = findViewById(R.id.toolbar3)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
         title = "   Описание продукта"
           var imageUri1: Uri? = null




         val  product = intent.getParcelableExtra(Product::class.java.simpleName) as Product?

        nametext.setText(product?.name)
        pricetext.setText(product?.price)
        contexttext.setText(product?.contextpr)


          imageUri1 = product?.image?.toUri()
          if (product?.image != "null") imageTv.setImageURI(imageUri1) else imageTv.setImageResource(R.drawable.qw)
         // try { imageTv.setImageURI(imageUri1) } catch (_:Exception) { imageTv.setImageResource(R.drawable.qw) }


        photoPickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK) { imageUri1 = result.data?.data
                imageTv.setImageURI(imageUri1)}}


        button1.setOnClickListener { ;
            if (nametext.text.isEmpty()) return@setOnClickListener
                         val intent = Intent(this,Activity2::class.java)
            if (product != null) {
                intent.putExtra(Product::class.java.simpleName,Product(nametext.text.toString(),pricetext.text.toString(),contexttext.text.toString(),imageUri1.toString()))
                setResult(RESULT_OK,intent)
                finish()
            }
        }
        button2.setOnClickListener { finishAffinity() }

        imageTv.setOnClickListener {

            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            photoPickerLauncher.launch(photoPickerIntent)
        }


    }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menuexit,menu)
            return super.onCreateOptionsMenu(menu) }


        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            AlertDialog.Builder(this).setTitle("Выход из программы").setMessage("Действительно выйти?")
                .setPositiveButton("Да") { s,v -> finishAffinity()}
                .setNegativeButton("Нет") {s,v -> s.cancel()}.create().show()
            return super.onOptionsItemSelected(item) }
}