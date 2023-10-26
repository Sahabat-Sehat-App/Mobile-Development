package com.capstone.sahabatsehat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.viewpager2.widget.ViewPager2

class OnboardingFragment1 : Fragment() {
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Isi dengan tindakan yang ingin Anda lakukan saat tombol "Back" ditekan
            val viewPager = activity?.findViewById<ViewPager2>(R.id.viewpager)
            viewPager?.currentItem = 0 // Ganti 0 dengan indeks langkah yang ingin Anda tuju
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Daftarkan callback di onCreate untuk menangani penekanan tombol "Back"
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
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