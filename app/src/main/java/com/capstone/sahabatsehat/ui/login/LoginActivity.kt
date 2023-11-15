package com.capstone.sahabatsehat.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.sahabatsehat.ForgotPasswordActivity
import com.capstone.sahabatsehat.HomeActivity
import com.capstone.sahabatsehat.data.api.ApiService
import com.capstone.sahabatsehat.data.preferences.UserModel
import com.capstone.sahabatsehat.data.preferences.UserPreferences
import com.capstone.sahabatsehat.data.response.LoginRequest
import com.capstone.sahabatsehat.data.response.LoginResponse
import com.capstone.sahabatsehat.databinding.ActivityLoginBinding
import com.capstone.sahabatsehat.utils.ProgressLoading
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Setting")

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var backPressedOnce = true
    private lateinit var progressLoading: ProgressLoading
    private lateinit var actionLoading: View
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressLoading = ProgressLoading()
        actionLoading = binding.proggressBar
//        val loginViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        ).get(LoginViewModel::class.java)



       initAction()
        onBack()


        binding.forgot.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    fun initAction(){
        binding.button.setOnClickListener{
            actionLogin()
        }
    }
    private fun actionLogin() {
        val request= LoginRequest()
            val emailEditText = binding.edEmail
            val passwordEditText = binding.edPassword
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

        val callApi=ApiService.getApi()
        callApi?.login(email, password)?.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val user= response.body()
                Log.e("accessToken",user!!.loginResult.accessToken)
                Log.e("email",user!!.loginResult.email)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("error", t.message!!)
            }

        })
    }

    private fun onBack() {
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
        binding.proggressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}