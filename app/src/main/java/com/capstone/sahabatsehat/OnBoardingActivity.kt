package com.capstone.sahabatsehat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.capstone.sahabatsehat.databinding.ActivityOnboardingBinding


class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter=ViewPagerAdapter(this)
        dotIndicator()
        onBackPress()
    }

    private fun dotIndicator(){
        val adapter=ViewPagerAdapter(this)
        binding.viewpager.adapter=adapter
        binding.dotsIndicator.attachTo(binding.viewpager)
    }
    private fun onBackPress(){
        val alertDialog=AlertDialog.Builder(this)
        alertDialog.setTitle("Konfirmasi Exit")
        alertDialog.setMessage("Anda yakin ingin keluar dari aplikasi?")
        alertDialog.setPositiveButton("Yes"){_,_->
            finish()
        }
        alertDialog.setNegativeButton("Tidak"){_,_->
            alertDialog.show()
        }
    }
}