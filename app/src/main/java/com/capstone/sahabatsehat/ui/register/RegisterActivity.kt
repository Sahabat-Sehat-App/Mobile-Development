package com.capstone.sahabatsehat.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.sahabatsehat.databinding.ActivityRegisterBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.ui.login.LoginActivity
import com.capstone.sahabatsehat.util.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

//        enableButton()
        buttonListener()
        viewModelSetup()
    }

    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(dataStore), this))[RegisterViewModel::class.java]

        viewModel.isLoading.observe(this){
            showLoading(it)
        }
    }


    private fun buttonListener(){
        binding.btnSingup.setOnClickListener{
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val nohp = binding.etNohp.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.registerUser(name, email, password, nohp)
            viewModel.registerdata.observe(this){
                if(it != null){
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            viewModel.snackbarText.observe(this){
                it.getContentIfNotHandled()?.let { snackBarText ->
                    showToast(snackBarText)
                }
            }
        }
    }



    private fun enableButton() {
        val name = !binding.etName.text.isNullOrBlank()
        val email = !binding.etEmail.text.isNullOrBlank()
        val nohp = !binding.etNohp.text.isNullOrBlank()
        val password = !binding.etPassword.text.isNullOrBlank()
        val repeatPassword = !binding.etConfirmPassword.text.isNullOrBlank()

        if(repeatPassword != password){
            showToast("Confirmasi pasword tidak sama dengan password")
        }

        if(name && email && nohp && password && repeatPassword){
            binding.btnSingup.isEnabled = true
        }else{
            binding.btnSingup.isEnabled = false

        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}