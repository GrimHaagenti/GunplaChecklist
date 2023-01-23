package com.example.listas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.listas.GunplaListActivity
import com.example.listas.databinding.FragmentMainMenuBinding
import com.example.listas.dataclasses.gunplaItem

class MainMenuFragment(val suppManager: FragmentManager, val parent: GunplaListActivity): Fragment() {

    private lateinit var binding: FragmentMainMenuBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainMenuBinding.inflate(inflater)
        return binding.root
    }
}