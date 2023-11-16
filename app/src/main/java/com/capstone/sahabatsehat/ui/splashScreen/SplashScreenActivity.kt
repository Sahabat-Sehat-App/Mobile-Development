package com.capstone.sahabatsehat.ui.splashScreen

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.capstone.sahabatsehat.HomeActivity
import com.capstone.sahabatsehat.databinding.ActivitySplashScreenBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.ui.login.LoginActivity
import com.capstone.sahabatsehat.ui.onBoarding.OnBoardingActivity
import com.capstone.sahabatsehat.util.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySplashScreenBinding
    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playAnimation()

        Handler(Looper.getMainLooper()).postDelayed({
            setupViewModel()
        }, 4000)

        supportActionBar?.hide()
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

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore), this)
        )[SplashScreenViewModel::class.java]

        viewModel.getUser().observe(this){user ->
            if(user.isLogin){
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                if (onBoardingFinished()){
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    val intent = Intent(this, OnBoardingActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun onBoardingFinished(): Boolean{
        val sharePref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharePref.getBoolean("Finished", false)
    }
    }
