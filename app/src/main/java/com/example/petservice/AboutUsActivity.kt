package com.example.petservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.widget.Button


class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val mapButton: Button = findViewById(R.id.Mapbtn)

        mapButton.setOnClickListener {
            val mapIntent: Intent = Uri.parse(
                "geo: 32.02430785016973, 35.87621691534178"
            ).let { location ->
                Intent(Intent.ACTION_VIEW, location)
            }

            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }
    }
}
