package com.example.listas.Activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.listas.DatabaseObject
import com.example.listas.R
import com.example.listas.databinding.ActivityMainMenuBinding
import com.example.listas.dataclasses.UserListsEnum
import com.google.android.gms.ads.AdRequest

class MainMenuActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val databaseObject = DatabaseObject

        println(databaseObject.DB.DataFormat)

        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
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

        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha_blink_slow)
        binding.bg3.startAnimation(anim)

        setContentView(binding.root)

    }


}