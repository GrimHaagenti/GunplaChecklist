package com.example.listas.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listas.R
import com.example.listas.databinding.ActivityDetailsPageBinding
import com.example.listas.databinding.ActivityMyListsBinding

class MyListsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyListsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListsBinding.inflate(layoutInflater)


        setContentView(R.layout.activity_my_lists)
    }
}