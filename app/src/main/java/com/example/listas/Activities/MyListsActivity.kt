package com.example.listas.Activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.GunplaListModelView
import com.example.listas.MyListsRecyclerViewAdapter
import com.example.listas.R
import com.example.listas.databinding.ActivityMyListsBinding
import com.example.listas.dataclasses.GunplaItem
import com.example.listas.dataclasses.UserListsEnum
import com.google.android.gms.ads.AdRequest
import com.google.android.material.snackbar.Snackbar

class MyListsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMyListsBinding

    val gunplalistmodelview by viewModels<GunplaListModelView>()
    lateinit var myListsRecyclerViewAdapter: MyListsRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyListsBinding.inflate(layoutInflater)

        val adRequest = AdRequest.Builder().build()
        binding.adView3.loadAd(adRequest)

        myListsRecyclerViewAdapter = MyListsRecyclerViewAdapter(this, gunplalistmodelview.getItemsOnCurrentList())

        binding.currentMyListName.text = gunplalistmodelview.databaseObject.currentList.name
        binding.currentMyListIcon.setImageResource(gunplalistmodelview.getCurrentListIcon())

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val item : GunplaItem = gunplalistmodelview.getItemsOnCurrentList()[viewHolder.adapterPosition]
                val position = viewHolder.adapterPosition
                manageSendElementForward(item.id)
                if (gunplalistmodelview.databaseObject.currentList.ordinal < UserListsEnum.DISPLAY.ordinal ) {
                    Snackbar.make(binding.currentListRecyclerView,
                        "Moved " + item.name + " to next list",
                        Snackbar.LENGTH_LONG)
                        .show()
                }else
                {
                    Snackbar.make(binding.currentListRecyclerView,
                        "Can't move " + item.name + " further",
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            }


        }).attachToRecyclerView(binding.currentListRecyclerView)

        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val item: GunplaItem =
                    gunplalistmodelview.getItemsOnCurrentList()[viewHolder.adapterPosition]
                val position = viewHolder.adapterPosition
                manageSendElementBack(item.id)

                if (gunplalistmodelview.databaseObject.currentList.ordinal > 0) {
                    Snackbar.make(binding.currentListRecyclerView,
                        "Moved " + item.name + " to previous list",
                        Snackbar.LENGTH_LONG)
                        .show()
                }else
                {
                    Snackbar.make(binding.currentListRecyclerView,
                        "Can't move " + item.name + " further",
                        Snackbar.LENGTH_LONG)
                        .show()
                }
            }

        }).attachToRecyclerView(binding.currentListRecyclerView)

        binding.iconMyListButton.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Change to List")
                .setItems(gunplalistmodelview.databaseObject.enumNamesList
                    , DialogInterface.OnClickListener{dialog, which ->

                        if(UserListsEnum.values()[which] != null){
                            gunplalistmodelview.changeCurrentList(UserListsEnum.values()[which])
                            binding.currentMyListName.text = gunplalistmodelview.databaseObject.currentList.name
                            binding.currentMyListIcon.setImageResource(gunplalistmodelview.getCurrentListIcon())
                            changeList()
                        }

                    })
            dialog.show()
        }


        binding.goForwardMyListButton.setOnClickListener{
            gunplalistmodelview.manageForwardListButton()
            binding.currentMyListName.text = gunplalistmodelview.databaseObject.currentList.name
            binding.currentMyListIcon.setImageResource(gunplalistmodelview.getCurrentListIcon())
            changeList()
        }

        binding.goBackMyListButton.setOnClickListener{
            gunplalistmodelview.manageBackListButton()
            binding.currentMyListName.text = gunplalistmodelview.databaseObject.currentList.name
            binding.currentMyListIcon.setImageResource(gunplalistmodelview.getCurrentListIcon())
            changeList()
        }

        binding.goMenuButtonMyLists.setOnClickListener {
            finish()
        }

        binding.currentListRecyclerView.adapter = myListsRecyclerViewAdapter

        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha_blink_slow)
        binding.bg5.startAnimation(anim)

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

    fun changeList(){
        myListsRecyclerViewAdapter.updateList(gunplalistmodelview.getItemsOnCurrentList())
    }



    fun manageDeleteElementFromList(itemId : Int){
        gunplalistmodelview.clearItemFromList(itemId)
        changeList()
    }
    fun manageSendElementBack(itemId: Int){
        gunplalistmodelview.sendItemToPreviousList(itemId)
        changeList()
    }
    fun manageSendElementForward(itemId: Int){
        gunplalistmodelview.sendItemToNextList(itemId)
        changeList()
    }

    fun detailActivityStart(id: Int){
        val intent = Intent(this, DetailsPageActivity::class.java)
        var intentExtras: Bundle = Bundle(2)
        intentExtras.putInt("chosenGunplaId", id)
        intentExtras.putBoolean("fromGunplaList", false)
        intent.putExtra("extras", intentExtras)
        startActivity(intent)
    }


}