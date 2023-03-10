package com.example.nolimits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val btnCamera = findViewById<Button>(R.id.btn_camera)
        val btnGallery = findViewById<Button>(R.id.btn_gallery)
        val btnList = findViewById<Button>(R.id.btn_list)
        val btnForm = findViewById<Button>(R.id.btn_form)

        btnCamera.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
        btnGallery.setOnClickListener {
            val intent = Intent(this, GalleryData::class.java)
            startActivity(intent)
        }
        btnList.setOnClickListener {
            val intent = Intent(this, ListData::class.java)
            startActivity(intent)
        }
        btnForm.setOnClickListener {
            val intent = Intent(this, ReservationForm::class.java)
            startActivity(intent)
        }
    }
}