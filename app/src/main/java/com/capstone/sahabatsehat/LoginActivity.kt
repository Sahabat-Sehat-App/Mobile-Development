package com.capstone.sahabatsehat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.capstone.sahabatsehat.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var backPressedOnce = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBack()
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
    private fun onBack(){
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedOnce) {
                    val alertDialog = AlertDialog.Builder(this@LoginActivity)
                    alertDialog.setTitle("Konfirmasi Exit")
                    alertDialog.setMessage("Anda yakin ingin keluar dari aplikasi?")
                    alertDialog.setPositiveButton("Yes") { _, _ ->
                        finishAffinity()
                    }
                    alertDialog.setNegativeButton("No") { _, _ ->
                        backPressedOnce = false // Setel kembali ke false untuk penanganan selanjutnya
                    }
                    alertDialog.show()
                } else {
                    backPressedOnce = true
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

}