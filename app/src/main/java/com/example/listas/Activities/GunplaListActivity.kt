package com.example.listas.Activities

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.DatabaseObject
import com.example.listas.GunplaListModelView
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.databinding.ActivityGunplaListBinding
import com.example.listas.dataclasses.UserListsEnum
import androidx.activity.viewModels


class GunplaListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGunplaListBinding
    var databaseObject = DatabaseObject

    var currentList :UserListsEnum = UserListsEnum.WANTED
    //val gunplalistmodelview by viewModels<GunplaListModelView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGunplaListBinding.inflate(layoutInflater)



        val manager = GridLayoutManager(parent,3)

        binding.goForwardListButton.setOnClickListener{
            manageForwardListButton()
        }
        binding.goBackListButton.setOnClickListener{
            manageBackListButton()
        }

        binding.gunplaRecyclerView.layoutManager = manager
        binding.gunplaRecyclerView.adapter = GunplaRecyclerViewAdapter(this, databaseObject.DB.gunplaDatabase)

        binding.goBackButtonList.setOnClickListener{
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        }
        setContentView(binding.root)
    }

    fun manageForwardListButton(){
        when(currentList){
            UserListsEnum.WANTED -> {
                currentList = UserListsEnum.BACKLOG
                binding.currentListIcon.setColorFilter(2)
            }
            UserListsEnum.BACKLOG -> {
                currentList = UserListsEnum.STARTED
                binding.currentListIcon.setColorFilter(3)
            }
            UserListsEnum.STARTED -> {
                currentList = UserListsEnum.ASSEMBLED
                binding.currentListIcon.setColorFilter(4)
            }
            UserListsEnum.ASSEMBLED -> {
                currentList = UserListsEnum.CUSTOM
                binding.currentListIcon.setColorFilter(5)
            }
            UserListsEnum.CUSTOM -> {
                currentList = UserListsEnum.FINISHED
                binding.currentListIcon.setColorFilter(6)
            }
            UserListsEnum.FINISHED -> {
                currentList = UserListsEnum.DISPLAY
                binding.currentListIcon.setColorFilter(7)
            }
            UserListsEnum.DISPLAY -> {
                currentList = UserListsEnum.WANTED
                binding.currentListIcon.setColorFilter(0)
            }
        }
    }
    fun manageBackListButton(){
        when(currentList){
            UserListsEnum.WANTED -> {
                currentList = UserListsEnum.DISPLAY
                binding.currentListIcon.setColorFilter(7)
            }
            UserListsEnum.BACKLOG -> {
                currentList = UserListsEnum.WANTED
                binding.currentListIcon.setColorFilter(0)
            }
            UserListsEnum.STARTED -> {
                currentList = UserListsEnum.BACKLOG
                binding.currentListIcon.setColorFilter(2)
            }
            UserListsEnum.ASSEMBLED -> {
                currentList = UserListsEnum.STARTED
                binding.currentListIcon.setColorFilter(3)
            }
            UserListsEnum.CUSTOM -> {
                currentList = UserListsEnum.ASSEMBLED
                binding.currentListIcon.setColorFilter(4)
            }
            UserListsEnum.FINISHED -> {
                currentList = UserListsEnum.CUSTOM
                binding.currentListIcon.setColorFilter(5)
            }
            UserListsEnum.DISPLAY -> {
                currentList = UserListsEnum.FINISHED
                binding.currentListIcon.setColorFilter(6)
            }
        }
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