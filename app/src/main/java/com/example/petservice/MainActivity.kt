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

        val nameET : EditText = findViewById(R.id.nameET)
        val emailET : EditText = findViewById(R.id.emailET)
        val phoneEt : EditText = findViewById(R.id.phoneET)
        val dateET:EditText=findViewById(R.id.dateET)


        val btnSendMsgToNextActivity: Button = findViewById(R.id.confirmBT)
        val resultBT: Button = findViewById(R.id.resultBT)
        val resultTV: TextView = findViewById(R.id.TextResult)



        var flag: String = "Check-up"
        val spinnerVal: Spinner = findViewById(R.id.spinner1)
        var options = arrayOf("Check-up", "Vaccine", "Dental cleaning", "Bath Services")
        spinnerVal.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        resultBT.setOnClickListener { view ->
            if (flag == "Check-up")
                resultTV.text = 50.toString();
            if (flag == "Vaccine")
                resultTV.text = 15.toString();
            if (flag == "Dental cleaning")
                resultTV.text = 70.toString();
            if (flag == "Bath Services")
                resultTV.text = 10.toString();
        }
        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }




        // insert data to database
        var cv = ContentValues()

        btnSendMsgToNextActivity.setOnClickListener {
          var cv = ContentValues()

            /*cv.put(CustomerProvider.Name,nameET.text.toString())
            cv.put(CustomerProvider.Phone,phoneEt.text.toString())
            cv.put(CustomerProvider.Email,emailET.text.toString())
           // cv.put(CustomerProvider.Date,dateET.text.toString())
            contentResolver.insert(CustomerProvider.CONTENT_URI,cv)*/

            val message: String = "Confirmation message sent to: " + phoneEt.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", message)
            startActivity(intent)
        }

        createNotificationChannel()


        resultBT.setOnClickListener {
            basicNotification()
        }

    }
    private var notificationId1: Int = 123
    private var notificationId2: Int = 234
    private var notificationId3 :Int = 345
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

/*private fun pendingNotification() {
val intent = Intent(this, MainActivity::class.java).apply {
flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
}
val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
val builder = NotificationCompat.Builder(this, channelId)
.setSmallIcon(R.mipmap.ic_launcher)
.setContentTitle("My notification")
.setContentText("Hello World!")
.setPriority(NotificationCompat.PRIORITY_DEFAULT)
.setContentIntent(pendingIntent)
.setAutoCancel(true)
with(NotificationManagerCompat.from(this)) {
notify(notificationId2, builder.build())
}
}
*/

    @RequiresApi(Build.VERSION_CODES.O)
    private fun actionsNotification() {
        val snoozeIntent = Intent(this, MainActivity::class.java).apply {
            action = "snooze"
            putExtra(EXTRA_NOTIFICATION_ID, 0)
        }
        val snoozePendingIntent: PendingIntent =
            PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My notification")
            .setContentText("Hello World!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(snoozePendingIntent)
            .addAction(
                R.drawable.ic_launcher_background, "Snooze",
                snoozePendingIntent
            )
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId2, builder.build())
        }
        val br: BroadcastReceiver = AfterNotification()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
//registerReceiver(br, filter)
    }

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








