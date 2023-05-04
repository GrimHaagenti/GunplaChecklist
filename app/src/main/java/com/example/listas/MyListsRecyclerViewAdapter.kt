package com.example.listas

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listas.Activities.MyListsActivity
import com.example.listas.databinding.ItemMyListItemBinding
import com.example.listas.dataclasses.GunplaItem
import com.example.listas.dataclasses.UserListsEnum

class MyListsRecyclerViewAdapter (
    val parent: MyListsActivity,
    private var currentList: List<GunplaItem>
        ):RecyclerView.Adapter<MyListsRecyclerViewAdapter.MyListItemVH>(){

    inner class MyListItemVH(binding: ItemMyListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val gradeIcon = binding.gradeIcon
        val name = binding.itemName
        val bgButton = binding.myListItemBg
        val sendToListButton = binding.SendToListButton
        val quickDelete = binding.quickDeleteButton

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListItemVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMyListItemBinding.inflate(layoutInflater)

        return MyListItemVH(binding)
    }

    override fun onBindViewHolder(holder: MyListItemVH, position: Int) {
        val element = currentList[position]

        holder.name.text = element.name
        when(element.grade){
           "Real Grade" -> {
               //Change icon
           }
        }
        holder.bgButton.setOnLongClickListener{
            parent.detailActivityStart(element.id)
            true
        }

        holder.quickDelete.setOnClickListener{

            // Create Dialog
            val dialog = AlertDialog.Builder(parent)
            dialog.setTitle("Are u sure you wanna delete?")
            dialog.setPositiveButton("Cancel") { _: DialogInterface, i: Int -> }
            dialog.setNegativeButton("Yes"){ dialogInterface: DialogInterface, i: Int -> parent.manageDeleteElementFromList(element.id)}
            dialog.show()


        }



        holder.sendToListButton.setOnClickListener{
            val dialog = AlertDialog.Builder(parent)
            dialog.setTitle("Change to List")
                .setItems(parent.gunplalistmodelview.databaseObject.enumNamesList
                    , DialogInterface.OnClickListener{dialog, which ->

                        if(UserListsEnum.values()[which] != null){
                            parent.gunplalistmodelview.sendItemtoList(element.id, UserListsEnum.values()[which])
                            parent.changeList()
                        }

                    })
            dialog.show()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList : List<GunplaItem>){

        this.currentList = newList
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}