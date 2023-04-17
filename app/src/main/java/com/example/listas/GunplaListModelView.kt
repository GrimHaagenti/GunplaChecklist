package com.example.listas

import androidx.lifecycle.ViewModel
import com.example.listas.dataclasses.UserListsEnum

class GunplaListModelView: ViewModel() {

    val databaseObject = DatabaseObject
    //lateinit var currentList : UserListsEnum


    fun setCurrentList(newCurrentList: UserListsEnum){
    //    currentList = newCurrentList
    }

}