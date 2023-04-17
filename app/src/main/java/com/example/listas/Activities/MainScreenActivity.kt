package com.example.listas.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listas.DatabaseObject
import com.example.listas.databinding.ActivityMainScreenBinding

class MainScreenActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMainScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseObject = DatabaseObject

        databaseObject.InitDatabases()



        binding = ActivityMainScreenBinding.inflate(layoutInflater)

        binding.enterAppButton.setOnClickListener{
            if(databaseObject.DB_Initialized) {
                val intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Database not ready", Toast.LENGTH_SHORT).show()
            }
        }

        setContentView(binding.root)
    }

}