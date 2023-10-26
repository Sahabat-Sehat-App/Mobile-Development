package com.capstone.sahabatsehat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button



class OnboardingFragment3 : Fragment() {


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding3, container, false)
        val startLog= view.findViewById<Button>(R.id.btnStart)

        startLog.setOnClickListener{
            val intent = Intent(activity, LoginActivity::class.java)

            // Mulai LoginActivity
            startActivity(intent)
        }
        return view
    }
}