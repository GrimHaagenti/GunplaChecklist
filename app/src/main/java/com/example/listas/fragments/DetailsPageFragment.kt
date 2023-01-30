package com.example.listas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.listas.GunplaListActivity
import com.example.listas.databinding.FragmentDetailsPageBinding
import com.example.listas.dataclasses.GunplaItem

class DetailsPageFragment(val suppManager:FragmentManager, val gunpla: GunplaItem, val parent:GunplaListActivity ):Fragment() {

    private lateinit var binding: FragmentDetailsPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = FragmentDetailsPageBinding.inflate(inflater)
            changeDetails(gunpla.FullName)
            binding.goBackButton.setOnClickListener { hideDetails()}
            return binding.root
    }

     fun changeDetails(fullName: String){
         binding.fullName.text = fullName
    }

    fun hideDetails(){
        parent.ReturnToLastFragment();
        }


}