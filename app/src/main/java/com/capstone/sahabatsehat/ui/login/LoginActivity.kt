package com.capstone.sahabatsehat.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.sahabatsehat.ForgotPasswordActivity
import com.capstone.sahabatsehat.HomeActivity
import com.capstone.sahabatsehat.data.model.UserModel
import com.capstone.sahabatsehat.databinding.ActivityLoginBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.ui.register.RegisterActivity
import com.capstone.sahabatsehat.util.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var backPressedOnce = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBack()
        setError()



        viewModelSetup()
        buttonListener()
    }

    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(dataStore), this))[LoginViewModel::class.java]

        viewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    private fun buttonListener(){
        binding.button.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            viewModel.loginUser(email, password)
            viewModel.logindata.observe(this){
                if(it != null){
                    viewModel.login(
                        UserModel(
                            it.loginResult.userId,
                            it.loginResult.name,
                            it.loginResult.email,
                            it.loginResult.nohp,
                            isLogin = true,
                            it.loginResult.accessToken,
                        )
                    )
                }

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            viewModel.snackbarText.observe(this){
                it.getContentIfNotHandled()?.let { snackBarText ->
                    showToast(snackBarText)
                }
            }
        }

        binding.forgot.setOnClickListener {
            val intent= Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.tvDaftar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
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
                        backPressedOnce = false
                    }
                    alertDialog.show()
                } else {
                    backPressedOnce = true
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}