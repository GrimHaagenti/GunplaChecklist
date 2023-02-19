package com.example.listas.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.databinding.ActivityGunplaListBinding
import com.example.listas.dataclasses.GunplaDatabase
import com.google.gson.Gson

class GunplaListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGunplaListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGunplaListBinding.inflate(layoutInflater)



        val manager = GridLayoutManager(parent,3)


        binding.gunplaRecyclerView.layoutManager = manager
        //binding.gunplaRecyclerView.adapter = GunplaRecyclerViewAdapter(this, gunplaList)

        setContentView(binding.root)
    }



}