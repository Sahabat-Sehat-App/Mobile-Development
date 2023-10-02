package com.capstone.sahabatsehat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sahabatsehat.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter=ViewPagerAdapter(this)
        dotIndicator()
    }

    private fun dotIndicator(){
        val adapter=ViewPagerAdapter(this)
        binding.viewpager.adapter=adapter
        binding.dotsIndicator.attachTo(binding.viewpager)
    }
}