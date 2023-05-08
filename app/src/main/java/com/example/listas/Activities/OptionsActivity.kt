package com.example.listas.Activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.example.listas.DatabaseObject
import com.example.listas.R
import com.example.listas.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {
    lateinit var binding : ActivityOptionsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOptionsBinding.inflate(layoutInflater)

        val databaseObject = DatabaseObject

        binding.clearListsButton.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Are u sure you wanna delete?")
            dialog.setNegativeButton("Cancel") { _: DialogInterface, i: Int -> }
            dialog.setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int -> databaseObject.clearUserLists(this)}
            dialog.show()
        }

        binding.goBackOptionsButton.setOnClickListener{
            finish()
        }

        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha_blink)
        binding.bg6.startAnimation(anim)

        setContentView(binding.root)
    }
}