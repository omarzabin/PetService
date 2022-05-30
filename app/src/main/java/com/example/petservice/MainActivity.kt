package com.example.petservice

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import android.net.ConnectivityManager
import android.os.Build
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameET: EditText = findViewById(R.id.nameET)
        val emailET: EditText = findViewById(R.id.emailET)
        val phoneEt: EditText = findViewById(R.id.phoneET)
        val dateET: EditText = findViewById(R.id.dateET)


        val btnSendMsgToNextActivity: Button = findViewById(R.id.confirmBT)
        val resultBT: Button = findViewById(R.id.resultBT)
        val resultTV: TextView = findViewById(R.id.TextResult)


        var flag: String = "Check-up"
        val spinnerVal: Spinner = findViewById(R.id.spinner1)
        var options = arrayOf("Check-up", "Vaccine", "Dental cleaning", "Bath Services")
        spinnerVal.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)

        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)

                resultBT.setOnClickListener { view ->
                    if (flag == "Check-up")
                        resultTV.text ="total is "+ 50.toString()
                    else if (flag == "Vaccine")
                        resultTV.text ="total is "+ 15.toString()
                    else if (flag == "Dental cleaning")
                        resultTV.text = "total is "+70.toString()
                    else if (flag == "Bath Services")
                        resultTV.text = "total is "+10.toString()
                   // Toast.makeText(this,"Test",Toast.LENGTH_LONG).show()
                }

            }


            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        // insert data to database
        btnSendMsgToNextActivity.setOnClickListener {
            var cv = ContentValues()

            cv.put(CustomerProvider.Name, nameET.text.toString())
            cv.put(CustomerProvider.Phone, phoneEt.text.toString())
            cv.put(CustomerProvider.Email, emailET.text.toString())
            cv.put(CustomerProvider.Date, dateET.text.toString())
            contentResolver.insert(CustomerProvider.CONTENT_URI, cv)

            val message: String = "Confirmation message sent to: " + phoneEt.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", message)
            basicNotification()
            startActivity(intent)


        }
        createNotificationChannel()

        resultBT.setOnClickListener {
            // add total TextView
        }

    }

    private var notificationId1: Int = 123
    private var notificationId2: Int = 234
    private var notificationId3: Int = 345
    private val channelId = "App_Channel.testNotification"
    private val description = "Trying to test different types notification"
    private fun basicNotification() {
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Notification")
            .setContentText("Your service has been booked!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId1, builder.build())
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "test_notification"
            val descriptionText = description
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}








