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
import com.example.listas.databinding.ActivityMainScreenBinding

class MainScreenActivity(val suppManager: FragmentManager, val parent: GunplaListActivity): AppCompatActivity() {

    private lateinit var binding : ActivityMainScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        binding.enterAppButton.setOnClickListener{
            //parent.SetFragment(nextFragment)
        }

        setContentView(binding.root)
    }

}