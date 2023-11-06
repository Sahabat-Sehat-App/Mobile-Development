package com.capstone.sahabatsehat

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.capstone.sahabatsehat.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playAnimation()
        splashAnim()
    }
    private fun playAnimation(){
        val fadeIn=ObjectAnimator.ofFloat(binding.imageLogo, View.ALPHA,0f,1f)
        fadeIn.duration=3000
        fadeIn.addListener(object:AnimatorListenerAdapter(){
            override fun onAnimationStart(animation: Animator) {
                if (animation != null) {
                    super.onAnimationStart(animation)
                }
                binding.imageLogo.visibility = View.VISIBLE
            }
        })
        fadeIn.start()

    }
    private fun splashAnim(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnBoardingActivity::class.java)
            startActivity(intent)
        }, 4000)
    }
    }
