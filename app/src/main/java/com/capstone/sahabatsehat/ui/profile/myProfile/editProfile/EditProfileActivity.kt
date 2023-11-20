package com.capstone.sahabatsehat.ui.profile.myProfile.editProfile

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sahabatsehat.R
import com.capstone.sahabatsehat.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    companion object{
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}