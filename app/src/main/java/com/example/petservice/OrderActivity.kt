package com.example.petservice

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        val nameET2: EditText = findViewById(R.id.nameET2)
        val phoneET2: EditText = findViewById(R.id.phoneET2)
        val emailET2: EditText = findViewById(R.id.emailET2)
        val dateET2: EditText = findViewById(R.id.dateET2)

        val idTV: TextView = findViewById(R.id.idTV)

        val nextBT: Button = findViewById(R.id.nextBT)
        val preBT: Button = findViewById(R.id.preBT)
        val updateBT: Button = findViewById(R.id.updateBT)
        val deleteBT: Button = findViewById(R.id.deleteBT)

        // select data from database

        var rs = contentResolver.query(
            CustomerProvider.CONTENT_URI,
            arrayOf(
                CustomerProvider._Id,
                CustomerProvider.Name,
                CustomerProvider.Email,
                CustomerProvider.Phone,
                CustomerProvider.Date
            ), null, null
        )
        nextBT.setOnClickListener {
            if (rs?.moveToNext()!!) {
                idTV.text = rs.getString(0)
                nameET2.setText(rs.getString(1))
                phoneET2.setText(rs.getString(3))
                emailET2.setText(rs.getString(2))
                dateET2.setText((rs.getString(4)))
            }
        }
        preBT.setOnClickListener {
            if (rs?.moveToPrevious()!!) {
                idTV.text = rs.getString(0)
                nameET2.setText(rs.getString(1))
                phoneET2.setText(rs.getString(3))
                emailET2.setText(rs.getString(2))
                dateET2.setText((rs.getString(4)))
            }
        }
        //update database
        updateBT.setOnClickListener {
            var cv = ContentValues()
            cv.put(CustomerProvider.Name, nameET2.text.toString())
            cv.put(CustomerProvider.Phone, phoneET2.text.toString())
            cv.put(CustomerProvider.Email, emailET2.text.toString())
            cv.put(CustomerProvider.Date, dateET2.text.toString())
            contentResolver.update(
                CustomerProvider.CONTENT_URI, cv, "_Id =?",
                arrayOf(idTV.text.toString())
            )

            rs?.requery()
            startActivity(Intent(this, OrderActivity::class.java))



        }

        //delete data
        deleteBT.setOnClickListener {
            contentResolver.delete(
                CustomerProvider.CONTENT_URI,
                "_Id =?",
                arrayOf(idTV.text.toString())
            )
            startActivity(Intent(this, OrderActivity::class.java))
            //rs?.requery()

        }


    }


}
