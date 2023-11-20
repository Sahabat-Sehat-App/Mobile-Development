package com.capstone.sahabatsehat.ui.profile.myProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sahabatsehat.R
import com.capstone.sahabatsehat.databinding.ActivityMyProfileBinding

class MyProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}