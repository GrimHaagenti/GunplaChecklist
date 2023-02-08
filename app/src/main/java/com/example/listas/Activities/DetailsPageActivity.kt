package com.example.listas.Activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.listas.GunplaListActivity
import com.example.listas.R
import com.example.listas.databinding.ActivityDetailsPageBinding
import com.example.listas.dataclasses.GunplaItem

class DetailsPageActivity(val suppManager:FragmentManager, val gunpla: GunplaItem, val parent: GunplaListActivity): AppCompatActivity() {

    private lateinit var binding: ActivityDetailsPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsPageBinding.inflate(layoutInflater)
        changeDetails(gunpla.FullName)
        binding.goBackButton.setOnClickListener { hideDetails()}

        setContentView(binding.root)
    }


     fun changeDetails(fullName: String){
         binding.fullName.text = fullName
    }

    fun hideDetails(){
        parent.ReturnToLastFragment();
        }


}