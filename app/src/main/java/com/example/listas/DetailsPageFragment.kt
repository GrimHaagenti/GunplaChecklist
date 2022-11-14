package com.example.listas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.listas.databinding.FragmentDetailsPageBinding

class DetailsPageFragment:Fragment() {

    private lateinit var binding: FragmentDetailsPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsPageBinding.inflate(inflater)
        return binding.root
    }
}