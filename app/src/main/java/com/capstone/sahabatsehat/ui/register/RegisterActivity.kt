package com.capstone.sahabatsehat.ui.register

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sahabatsehat.R

class RegisterActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val buttonSignup = findViewById<Button>(R.id.btn_singup)

        buttonSignup.setOnClickListener {
            println("Button sign up")
        }
    }
}