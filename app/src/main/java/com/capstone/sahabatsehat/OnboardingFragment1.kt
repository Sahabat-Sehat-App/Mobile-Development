package com.capstone.sahabatsehat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2

class OnboardingFragment1 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboarding1, container, false)
        val btnNext = view.findViewById<Button>(R.id.btnNext)
        val btnSkip = view.findViewById<Button>(R.id.btnSkip)
        val viewPager= activity?.findViewById<ViewPager2>(R.id.viewpager)

        btnNext.setOnClickListener {
            viewPager?.currentItem=1
        }


        btnSkip.setOnClickListener {
            viewPager?.currentItem=3
        }

        return view
    }
}