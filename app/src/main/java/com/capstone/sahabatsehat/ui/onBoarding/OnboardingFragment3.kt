package com.capstone.sahabatsehat.ui.onBoarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.capstone.sahabatsehat.ui.login.LoginActivity
import com.capstone.sahabatsehat.R


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
            requireActivity().finish()
            onBoardingFinished()
        }
        return view
    }

    private fun onBoardingFinished(){
        val sharePref = requireContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharePref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}