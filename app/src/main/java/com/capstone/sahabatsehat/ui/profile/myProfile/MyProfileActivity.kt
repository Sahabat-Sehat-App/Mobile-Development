package com.capstone.sahabatsehat.ui.profile.myProfile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.sahabatsehat.databinding.ActivityMyProfileBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMyProfileBinding
    private lateinit var viewModel:MyprofileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModelSetup()
        getUserProfile()
    }


    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(dataStore), this))[MyprofileViewModel::class.java]

        viewModel.isLoading.observe(this){
        showLoading(it)
        }
        viewModel.snackbarText.observe(this){
            it.getContentIfNotHandled()?.let { snackBarText ->
                showToast(snackBarText)
            }
        }
    }
    private fun getUserProfile(){
   viewModel.getUser().observe(this){
       if(it !=null){
           val whitespace = "          "
           viewModel.getUserById(it.id,it.accessToken)
           binding.email.text="Email     :${whitespace} ${it.email}"

           binding.name.text = "Nama Lengkap:${whitespace}${it.name}"

           binding.nohp.text="No.Hp:${whitespace}${it.nohp}"




           Log.d("MyProfileActivity", "User data received: $it")
       }
   }
    }
    
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}