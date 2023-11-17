package com.capstone.sahabatsehat.ui.profile.myProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.capstone.sahabatsehat.R
import com.capstone.sahabatsehat.data.preferences.UserModel
import com.capstone.sahabatsehat.databinding.ActivityMyProfileBinding
import com.capstone.sahabatsehat.preferences.UserPreference

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMyProfileBinding
    private lateinit var userPreference: UserPreference
    private lateinit var dataStore: DataStore<Preferences>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        userPreference= UserPreference.getInstance(dataStore)

    }

    private fun getUserProfile(user:UserModel){
        binding.email.text=user.email
        binding.name.text=user.name
        binding.nohp.text=user.nohp
    }
}