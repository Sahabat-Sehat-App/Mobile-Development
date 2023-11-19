package com.capstone.sahabatsehat.ui.booking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sahabatsehat.databinding.ActivityBookingBinding
import com.capstone.sahabatsehat.ui.booking.dummy.Doctors

class BookingActivity : AppCompatActivity() {
    lateinit var binding: ActivityBookingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        actionBar?.title = "Pemeriksaan Kesehatan"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding.rvDoctors.adapter = BookingAdapter(Doctors().data)
    }

}