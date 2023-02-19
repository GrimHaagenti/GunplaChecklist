package com.example.listas.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listas.DatabaseObject
import com.example.listas.databinding.ActivityMainScreenBinding

class MainScreenActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMainScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseObject : DatabaseObject = DatabaseObject

        databaseObject.PrintDataDebugGunpla()
        println(databaseObject.DB.DataFormat)


        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        binding.enterAppButton.setOnClickListener{
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)

        }

        setContentView(binding.root)
    }

}