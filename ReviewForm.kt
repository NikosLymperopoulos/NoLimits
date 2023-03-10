package com.example.nolimits

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.app.NotificationChannel

import android.app.NotificationManager

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import android.app.PendingIntent
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton

class ReviewForm : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_layout)

        val icon_btn = this.findViewById<ImageButton>(R.id.icon_button)

        icon_btn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        val intent = intent

        var placeName =  intent.getStringExtra("name")
        val initialText = findViewById<TextView>(R.id.initial_text)
        var newDesc = initialText.text.toString() + placeName
        initialText.text = newDesc

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = "my_channel_01"
        val name: CharSequence = getString(R.string.channel_name)
        val description = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_LOW
        val mChannel = NotificationChannel(id, name, importance)

        mChannel.description = description
        mChannel.enableLights(true)
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        mNotificationManager.createNotificationChannel(mChannel)

    }

    override fun onStart() {
        super.onStart()
        toastMessage("Your input will be used anonymously.")
    }

    override fun onStop() {
        // call the superclass method first
        super.onStop()
        val builder = NotificationCompat.Builder(this, "We will be waiting for you!")
        builder.setContentTitle("Review")
        builder.setContentText("Be sure to come back and check more restaurants!")
        builder.setSmallIcon(R.mipmap.login_image_round)
        builder.setAutoCancel(true)


        val pendingIntent = Intent(this, ListData::class.java)

        val contentIntent = PendingIntent.getActivity(this, 0, pendingIntent, PendingIntent.FLAG_ONE_SHOT)
        builder.setContentIntent(contentIntent)

        builder.setChannelId("my_channel_01")
        val managerCompat = NotificationManagerCompat.from(this)
        managerCompat.notify(1, builder.build())

    }


    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Your non submitted data will be lost.\nPlease click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}