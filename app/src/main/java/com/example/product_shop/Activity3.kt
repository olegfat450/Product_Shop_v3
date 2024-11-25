package com.example.product_shop

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity3 : AppCompatActivity() {

      private lateinit var imageTv: ImageView
       private lateinit var nametext: TextView
       private lateinit var pricetext: TextView
       private lateinit var contexttext: TextView
       private lateinit var button1: Button
       private lateinit var button2: Button
       private lateinit var toolbar: Toolbar



    @SuppressLint("MissingInflatedId")
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


             nametext.append(intent.getStringExtra("name"))
             pricetext.append(intent.getStringExtra("price"))
             contexttext.append(intent.getStringExtra("context"))

        val s = intent.getParcelableExtra("image") as Bitmap?
        imageTv.setImageBitmap(s)

        button1.setOnClickListener { finish() }
        button2.setOnClickListener { finishAffinity() }
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