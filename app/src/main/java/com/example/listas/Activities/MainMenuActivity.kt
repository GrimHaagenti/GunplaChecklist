package com.example.listas.Activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.listas.GunplaListActivity
import com.example.listas.R
import com.example.listas.databinding.ActivityMainMenuBinding

class MainMenuActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        binding.searchKitsButton.setOnClickListener {
            val intent = Intent(this, GunplaListActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)

    }


}