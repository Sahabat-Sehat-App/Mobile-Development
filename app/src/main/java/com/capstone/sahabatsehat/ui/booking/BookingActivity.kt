package com.capstone.sahabatsehat.ui.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sahabatsehat.R
import com.capstone.sahabatsehat.databinding.ActivityBookingBinding

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object{
        const val EXTRA_LAYANAN_ID = "extra_layanan_ID"
        const val EXTRA_DOCTOR_ID = "extra_doctor_ID"

    }
}