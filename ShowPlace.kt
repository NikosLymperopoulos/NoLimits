package com.example.nolimits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ShowPlace : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_place_layout)


        val intent = intent

        var placeName =  intent.getStringExtra("name")
        var originalPlaceName = placeName
        val newDescription = intent.getStringExtra("name") + "\n" + intent.getStringExtra("address")
        val hasAccess = intent.getStringExtra("access")
        val hasToilets = intent.getStringExtra("toilets")
        val pathToPics = "C:\\Users\\Nikos\\AndroidStudioProjects\\NoLimits\\app\\src\\main\\res\\drawable-v24\\"


        val placeDescription = findViewById<TextView>(R.id.place_name)
        val placeAccess = findViewById<TextView>(R.id.place_access)
        val placeToilets = findViewById<TextView>(R.id.place_toilets)

        val btnReview = findViewById<Button>(R.id.btn_review)

        if (placeName != null) {
            placeName = placeName.lowercase()
            placeName = placeName.replace(" ", "")
        }


        val placeImage = findViewById<View>(R.id.place_image) as ImageView

        val id = resources.getIdentifier("com.example.nolimits:drawable/$placeName", null, null)
        placeImage.setImageResource(id);

        placeDescription.text = newDescription
        if(hasAccess == "0") {
            val access = "Does not provide " + placeAccess.text
            placeAccess.text = access
        }
        else{
            val access = "Provides " + placeAccess.text
            placeAccess.text = access
        }
        if(hasToilets=="0"){
            val toilets = "Does not provide " + placeToilets.text
            placeToilets.text = toilets
        }
        else{
            val toilets = "Provides " + placeToilets.text
            placeToilets.text = toilets
        }

        btnReview.setOnClickListener {
            val intent = Intent(this, ReviewForm::class.java)
            intent.putExtra("name", originalPlaceName)
            startActivity(intent)
        }



    }
}