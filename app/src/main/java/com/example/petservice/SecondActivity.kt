package com.example.petservice

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import android.widget.*

class SecondActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)



        val message = intent.getStringExtra("key")

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.txvUserMessage).apply {
            text = message
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.my_fidst_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.orderItem->startActivity(Intent(this, OrderActivity::class.java))
            R.id.placeIteam -> startActivity(Intent(this, MainActivity::class.java))
            R.id.aboutUsItem-> startActivity(Intent(this,AboutUsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


}
