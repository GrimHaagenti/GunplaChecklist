package com.example.listas.Activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.listas.DatabaseObject
import com.example.listas.GunplaListModelView
import com.example.listas.GunplaRecyclerViewAdapter
import com.example.listas.databinding.ActivityGunplaListBinding
import com.example.listas.dataclasses.UserListsEnum
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.R
import com.example.listas.dataclasses.GunplaItem
import com.google.android.gms.ads.AdRequest


class GunplaListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityGunplaListBinding


    val gunplalistmodelview by viewModels<GunplaListModelView>()
    lateinit var gpRecyclerViewAdapter: GunplaRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGunplaListBinding.inflate(layoutInflater)

        gpRecyclerViewAdapter = GunplaRecyclerViewAdapter(this, gunplalistmodelview.databaseObject.DB.gunplaDatabase)

        val adRequest = AdRequest.Builder().build()
        binding.adView2.loadAd(adRequest)

        val manager = GridLayoutManager(parent,3)

        binding.currentGunplaListName.text = gunplalistmodelview.databaseObject.currentList.name

        binding.iconGunplaListButton.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            dialog.setItems(gunplalistmodelview.databaseObject.enumNamesList
                    , DialogInterface.OnClickListener{ dialog, which ->

                        if(UserListsEnum.values()[which] != null){
                            gunplalistmodelview.changeCurrentList(UserListsEnum.values()[which])
                            binding.currentGunplaListName.text = gunplalistmodelview.databaseObject.currentList.name
                            binding.currentGunplaListIcon.setImageResource(gunplalistmodelview.getCurrentListIcon())
                            changeList()
                        }

                    })
            dialog.show()
        }

        binding.goForwardGunplaListButton.setOnClickListener{
            gunplalistmodelview.manageForwardListButton()
            binding.currentGunplaListName.text = gunplalistmodelview.databaseObject.currentList.name
            binding.currentGunplaListIcon.setImageResource(gunplalistmodelview.getCurrentListIcon())
            changeList()

        }
        binding.goBackGunplaListButton.setOnClickListener{
            gunplalistmodelview.manageBackListButton()
            binding.currentGunplaListName.text = gunplalistmodelview.databaseObject.currentList.name
            binding.currentGunplaListIcon.setImageResource(gunplalistmodelview.getCurrentListIcon())
            changeList()
        }

        binding.gunplaRecyclerView.layoutManager = manager

        binding.gunplaRecyclerView.adapter = gpRecyclerViewAdapter



        binding.goBackButtonGunplaList.setOnClickListener{
            finish()
        }
        setContentView(binding.root)

    }

    override fun onPause() {
        super.onPause()
        gunplalistmodelview.manageOnPauseOnDestroy(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        gunplalistmodelview.manageOnPauseOnDestroy(this)

    }

    fun manageOnClickItem(itemId:Int){
        gunplalistmodelview.manageClickOnItem(itemId)

    }

    fun changeList(){

        val idList = gunplalistmodelview.getCurrentIdsOnList()

        gunplalistmodelview.databaseObject.DB.gunplaDatabase.forEachIndexed{ index: Int, gunplaItem: GunplaItem ->
            gpRecyclerViewAdapter.notifyItemChanged(index, idList.contains(index))
        }

    }


    fun detailActivityStart(id: Int ){
        val intent = Intent(this, DetailsPageActivity::class.java)
        var intentExtras: Bundle = Bundle(2)
        intentExtras.putInt("chosenGunplaId", id)
        intentExtras.putBoolean("fromGunplaList", true)
        intent.putExtra("extras", intentExtras)
        startActivity(intent)
    }

}