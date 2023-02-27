package com.example.listas.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.DatabaseObject
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.databinding.ActivityGunplaListBinding

class GunplaListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGunplaListBinding
    var databaseObject = DatabaseObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGunplaListBinding.inflate(layoutInflater)



        val manager = GridLayoutManager(parent,3)



        binding.gunplaRecyclerView.layoutManager = manager
        binding.gunplaRecyclerView.adapter = GunplaRecyclerViewAdapter(this, databaseObject.DB.gunplaDatabase)

        binding.goBackButtonList.setOnClickListener{
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }



    fun detailActivityStart(id: Int){
        val intent = Intent(this, DetailsPageActivity::class.java)
        intent.putExtra("chosenGunplaId", id)
        startActivity(intent)
    }

    /*fun (id: Int){
        val intent = Intent(parent, DetailsPageActivity::class.java)
        intent.putExtra("chosenGunplaId", id)
        parent.StartActivity(intent)

        StartActivity(intent)
    }
*/

}