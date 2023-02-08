package com.example.listas.Activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.GunplaListActivity
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.R
import com.example.listas.databinding.ActivityGunplaListBinding
import com.example.listas.dataclasses.GunplaItem

class GunplaListActivity(val suppManager: FragmentManager, val gunplaList: List<GunplaItem>, val parent: GunplaListActivity): AppCompatActivity() {

    private lateinit var binding : ActivityGunplaListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val manager = GridLayoutManager(parent,3)

        binding = ActivityGunplaListBinding.inflate(layoutInflater)
        binding.gunplaRecyclerView.layoutManager = manager
        binding.gunplaRecyclerView.adapter = GunplaRecyclerViewAdapter(this, gunplaList, parent)

        setContentView(binding.root)
    }



}