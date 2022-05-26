package com.example.petservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val nameET2 :EditText = findViewById(R.id.nameET2)
        val phoneET2:EditText  = findViewById(R.id.phoneET2)
        val emailET2:EditText  = findViewById(R.id.emailET2)
        //val dateET:EditText  = findViewById(R.id.dateET)

        var rs = contentResolver.query(
            CustomerProvider.CONTENT_URI,
            arrayOf(
                CustomerProvider.Name,
                CustomerProvider.Email,
                CustomerProvider.Phone
            ), null, null
        )
        if (rs?.moveToNext()!!) {
            nameET2.setText(rs.getString(0))
            phoneET2.setText(rs.getString(1))
            emailET2.setText(rs.getString(2))
        }


    }


}