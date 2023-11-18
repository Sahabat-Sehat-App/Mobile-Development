package com.capstone.sahabatsehat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.sahabatsehat.databinding.FragmentProfilBinding
import com.capstone.sahabatsehat.ui.profile.myProfile.MyProfileActivity


class ProfilFragment : Fragment() {
private var _binding:FragmentProfilBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
       _binding=FragmentProfilBinding.inflate(inflater,container,false)
        val root:View= binding.root



        return root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


