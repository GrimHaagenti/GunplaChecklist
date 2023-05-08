package com.example.listas

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.listas.dataclasses.GunplaItem
import com.example.listas.dataclasses.UserListsEnum

class GunplaListModelView: ViewModel() {

    val databaseObject = DatabaseObject
    //lateinit var currentList : UserListsEnum


    fun manageOnPauseOnDestroy(activity: Activity){
        databaseObject.writeUserLists(activity)
    }

    fun changeCurrentList( newList: UserListsEnum){
        databaseObject.currentList = newList

    }

    fun getCurrentIdsOnList(): ArrayList<Int>{
        return databaseObject.getIdsOnCurrentList(databaseObject.currentList)
    }

    fun manageClickOnItem(itemId:Int): Boolean{
        return databaseObject.manageClickOnItem(databaseObject.currentList, itemId)
    }

    fun getItemsOnCurrentList(): ArrayList<GunplaItem>{

        val ids = databaseObject.getIdsOnCurrentList(databaseObject.currentList)
        val gunplaItemsOnList = databaseObject.getItemsInList(ids)



        return gunplaItemsOnList
    }

    fun sendItemtoList(itemId:Int, targetList: UserListsEnum){
        clearItemFromList(itemId)
        databaseObject.addItemToList(itemId, targetList)
    }

    fun getCurrentListIcon():Int
    {
        when(databaseObject.currentList){
            UserListsEnum.WANTED ->
            {
                return R.drawable.number_1
            }
            UserListsEnum.BACKLOG -> {
                return R.drawable.number_2
            }
            UserListsEnum.STARTED -> {
                return R.drawable.number_3
            }
            UserListsEnum.ASSEMBLED -> {
                return R.drawable.number_4
            }
            UserListsEnum.CUSTOM -> {
                return R.drawable.number_5
            }
            UserListsEnum.FINISHED -> {
                return R.drawable.number_6
            }
            UserListsEnum.DISPLAY -> {
                return R.drawable.number_7
            }
        }
    }
    fun clearItemFromList(itemId: Int){
        databaseObject.clearItemFromList(itemId)
    }

    fun sendItemToNextList(itemId: Int){

        var nextListNumber = databaseObject.currentList.ordinal + 1
        nextListNumber = Math.min(nextListNumber, UserListsEnum.DISPLAY.ordinal)

        databaseObject.clearItemFromList(itemId)
        databaseObject.addItemToList(itemId, UserListsEnum.values()[nextListNumber])
    }
    fun sendItemToPreviousList(itemId: Int){

        var previousListNumber = databaseObject.currentList.ordinal - 1
        previousListNumber = Math.max(previousListNumber, 0)

        databaseObject.clearItemFromList(itemId)
        databaseObject.addItemToList(itemId, UserListsEnum.values()[previousListNumber])
    }


    fun manageForwardListButton(){

        var newListNumber = databaseObject.currentList.ordinal + 1
        if  (newListNumber > UserListsEnum.DISPLAY.ordinal){
            newListNumber = UserListsEnum.WANTED.ordinal;
        }

        var nextList = UserListsEnum.values()[newListNumber]

        changeCurrentList(nextList)
    }

    fun manageBackListButton() {
        var newListNumber = databaseObject.currentList.ordinal - 1
        if  (newListNumber < UserListsEnum.WANTED.ordinal){
            newListNumber = UserListsEnum.DISPLAY.ordinal;
        }

        var nextList = UserListsEnum.values()[newListNumber]

        changeCurrentList(nextList)
    }

}