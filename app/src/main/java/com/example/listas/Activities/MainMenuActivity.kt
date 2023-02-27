package com.example.listas.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listas.DatabaseObject
import com.example.listas.databinding.ActivityMainMenuBinding

class MainMenuActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var databaseObject = DatabaseObject

        println(databaseObject.DB.DataFormat)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        binding.searchKitsButton.setOnClickListener {
            val intent = Intent(this, GunplaListActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)

    }


}