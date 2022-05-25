package com.example.petservice

import android.os.Bundle
import android.view.Menu
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

}
