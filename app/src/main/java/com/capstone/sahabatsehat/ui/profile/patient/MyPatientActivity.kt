package com.capstone.sahabatsehat.ui.profile.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sahabatsehat.R
import com.capstone.sahabatsehat.databinding.ActivityMyPatientBinding

class MyPatientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPatientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}