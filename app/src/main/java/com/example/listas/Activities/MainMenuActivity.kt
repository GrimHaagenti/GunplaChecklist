package com.example.listas.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.listas.DatabaseObject
import com.example.listas.databinding.ActivityMainMenuBinding
import com.example.listas.dataclasses.UserListsEnum

class MainMenuActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseObject = DatabaseObject

        println(databaseObject.DB.DataFormat)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)

        databaseObject.currentList = UserListsEnum.WANTED
        binding.searchKitsButton.setOnClickListener {
            val intent = Intent(this, GunplaListActivity::class.java)
            startActivity(intent)
        }

        binding.myListsButton.setOnClickListener{
            val intent = Intent(this, MyListsActivity::class.java)
            startActivity(intent)
        }

        binding.optionsButton.setOnClickListener{
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)

    }


}