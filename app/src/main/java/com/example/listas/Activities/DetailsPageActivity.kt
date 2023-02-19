package com.example.listas.Activities

import androidx.appcompat.app.AppCompatActivity
import com.example.listas.databinding.ActivityDetailsPageBinding

class DetailsPageActivity: AppCompatActivity() {

    private lateinit var binding: ActivityDetailsPageBinding





    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsPageBinding.inflate(layoutInflater)
        //changeDetails(gunpla.FullName)
        binding.goBackButton.setOnClickListener { hideDetails()}

        setContentView(binding.root)
    }*/


     fun changeDetails(fullName: String){
         binding.fullName.text = fullName
    }

    fun hideDetails(){
        //parent.ReturnToLastFragment();
        }


}