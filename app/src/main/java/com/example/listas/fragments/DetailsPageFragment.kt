package com.example.listas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.listas.databinding.FragmentDetailsPageBinding

class DetailsPageFragment(val suppManager:FragmentManager):Fragment() {

    private lateinit var binding: FragmentDetailsPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.goBackButton.setOnClickListener { hideDetails()}
        binding = FragmentDetailsPageBinding.inflate(inflater)
        return binding.root
    }

     fun changeDetails(fullName: String){
         binding.fullName.text = fullName
    }

    fun hideDetails(){
        suppManager.beginTransaction().apply {
            this.hide(this@DetailsPageFragment)
            commit()
        }
    }

}