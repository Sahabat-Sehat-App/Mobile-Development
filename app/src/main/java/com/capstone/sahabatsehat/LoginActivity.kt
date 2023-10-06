package com.capstone.sahabatsehat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sahabatsehat.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setError()
    }

    private fun setError() {
        val emailEditText = binding.edEmail
        val passwordEditText = binding.edPassword
        val email=emailEditText.text.toString()
        val password=passwordEditText.text.toString()

        if (email.isEmpty()) {
            emailEditText.error = "Email harus diisi"
            return
        }

        if (password.isEmpty()) {
            passwordEditText.error = "Password harus diisi"
            return
        }
    }
}