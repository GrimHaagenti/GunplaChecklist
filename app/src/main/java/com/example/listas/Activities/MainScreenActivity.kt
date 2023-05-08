package com.example.listas.Activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.listas.DatabaseObject
import com.example.listas.R
import com.example.listas.databinding.ActivityMainScreenBinding
import com.google.android.gms.ads.MobileAds

class MainScreenActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMainScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)
        val databaseObject = DatabaseObject

        databaseObject.InitDatabases()
        databaseObject.readUserLists(this)


        binding = ActivityMainScreenBinding.inflate(layoutInflater)

        binding.enterAppButton.setOnClickListener{
            if(databaseObject.DB_Initialized) {
                val intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Database not ready", Toast.LENGTH_SHORT).show()
            }
        }
        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha_blink)
        var animBg = AnimationUtils.loadAnimation(this, R.anim.alpha_blink_slow)
        binding.TapToEnterText.startAnimation(anim)
        binding.bg4.startAnimation(animBg)

        setContentView(binding.root)
    }

}