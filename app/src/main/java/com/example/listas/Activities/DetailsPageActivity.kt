package com.example.listas.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.DatabaseObject
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.databinding.ActivityDetailsPageBinding
import com.example.listas.databinding.ActivityGunplaListBinding
import com.squareup.picasso.Picasso

class DetailsPageActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsPageBinding

    var databaseObject = DatabaseObject




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsPageBinding.inflate(layoutInflater)

        val gunplaId = intent.extras?.getInt("chosenGunplaId")?: -1
        if(gunplaId < 0) {Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT)}
        else {changeDetails(gunplaId)}

        binding.goBackButtonDetails.setOnClickListener {
            val intent = Intent(this, GunplaListActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
    fun changeDetails(id: Int){
         binding.fullName.text = databaseObject.DB.gunplaDatabase[id].fullName
        getBoxArt( databaseObject.DB.gunplaDatabase[id].boxArtURL,binding.boxArt)
        binding.scaleText.text = databaseObject.DB.gunplaDatabase[id].scale
    }

    fun getBoxArt(url: String, imageView: ImageView?) {

        val pic = Picasso.Builder(this).build()
        pic.setLoggingEnabled(true)
        pic.load(url).
            into(imageView)

    }
}