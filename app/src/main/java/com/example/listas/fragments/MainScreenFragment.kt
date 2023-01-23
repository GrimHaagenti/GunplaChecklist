package com.example.listas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.listas.GunplaListActivity
import com.example.listas.Screens
import com.example.listas.databinding.FragmentDetailsPageBinding
import com.example.listas.databinding.FragmentMainScreenBinding

class MainScreenFragment(val suppManager: FragmentManager, val parent: GunplaListActivity): Fragment() {

    private lateinit var binding : FragmentMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainScreenBinding.inflate(inflater)
        binding.enterAppButton.setOnClickListener{
            //parent.SetFragment(nextFragment)
        }
        return binding.root
    }
}