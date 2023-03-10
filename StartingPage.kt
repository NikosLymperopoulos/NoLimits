package com.example.nolimits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class StartingPage : AppCompatActivity() {

    var mDatabaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.starting_page)

        mDatabaseHelper = DatabaseHelper(this);



        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        toastMessage("Remember to never share your login information with other people!")
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}