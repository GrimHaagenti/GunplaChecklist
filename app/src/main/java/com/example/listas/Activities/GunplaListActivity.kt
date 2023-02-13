package com.example.listas.Activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.GunplaListActivity
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.R
import com.example.listas.databinding.ActivityGunplaListBinding
import com.example.listas.dataclasses.GunplaDatabase
import com.example.listas.dataclasses.GunplaItem
import com.google.gson.Gson

class GunplaListActivity: AppCompatActivity() {

    private lateinit var binding : ActivityGunplaListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var gunplaDatabase: GunplaDatabase = ReadJson()

        var gunplaList = gunplaDatabase.gunplaDatabase

        val manager = GridLayoutManager(parent,3)

        binding = ActivityGunplaListBinding.inflate(layoutInflater)
        binding.gunplaRecyclerView.layoutManager = manager
        binding.gunplaRecyclerView.adapter = GunplaRecyclerViewAdapter(this, gunplaList)

        setContentView(binding.root)
    }


    fun ReadJson (): GunplaDatabase {
        val fileContent = getResourceAsText()
        println(fileContent)
        val gson = Gson()
        return gson.fromJson(fileContent, GunplaDatabase::class.java)
    }


    fun getResourceAsText(): String {
        val jsonData = applicationContext.resources.openRawResource(
            applicationContext.resources.getIdentifier(
                "gunpla_checklist_json_database",
                "raw", applicationContext.packageName
            )
        ).bufferedReader().use { it.readText() }
        return jsonData
    }


}