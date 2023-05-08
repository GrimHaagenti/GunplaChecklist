package com.example.listas
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences.Editor
import com.example.listas.dataclasses.GunplaDatabase
import com.example.listas.dataclasses.GunplaItem
import com.example.listas.dataclasses.UserLists
import com.example.listas.dataclasses.UserListsEnum
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson


object DatabaseObject {

    lateinit var DB: GunplaDatabase

    lateinit var userListsDatabase: UserLists

    var currentList : UserListsEnum = UserListsEnum.WANTED

    var enumNamesList = arrayOf<String>("Wanted", "Backlog", "Started", "Assambled", "Custom", "Finished", "On Display")

    var DB_Initialized = false
    private var database: DatabaseReference =
        Firebase.database("https://gunplachecklist-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("")

    fun InitDatabases(){
        getDatabaseInfo()
        initUserLists()

    }

    fun getDatabaseInfo() {

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val format = snapshot.child("DataFormat").getValue(String::class.java)?: return
                val version = snapshot.child("FormatVersion").getValue(Double::class.java)?: return
                var itemList: ArrayList<GunplaItem> = arrayListOf()
                snapshot.child("gunplaDatabase").children.forEach{
                    var item = it.getValue(GunplaItem::class.java)?:return
                    itemList.add(item)
                }
                DB = GunplaDatabase(format, version, itemList)
                DB_Initialized = true
            }

            override fun onCancelled(error: DatabaseError) {
                println("ERROOOOOOR: " + error.message)
            }
        })



    }

    fun getLists(): UserLists{
        return UserLists()
    }

    fun initUserLists(){

        //Try getting the user lists
        userListsDatabase = getLists();


    }

    fun getIdsOnCurrentList(currentList:UserListsEnum): ArrayList<Int>{
        val listOfIds: ArrayList<Int> = arrayListOf<Int>()

        when(currentList){
            UserListsEnum.WANTED ->
            {
                userListsDatabase.wanted.forEach{
                    listOfIds.add(it)
                }
            }

            UserListsEnum.BACKLOG ->
            {
                userListsDatabase.backlog.forEach{
                    listOfIds.add(it)
                }
            }
            UserListsEnum.STARTED ->
            {
                userListsDatabase.started.forEach{
                    listOfIds.add(it)
                }
            }
            UserListsEnum.ASSEMBLED ->
            {
                userListsDatabase.assembled.forEach{
                    listOfIds.add(it)
                }
            }
            UserListsEnum.CUSTOM -> {
                userListsDatabase.customizing.forEach{
                    listOfIds.add(it)
                }
            }
            UserListsEnum.FINISHED ->
            {
                userListsDatabase.finished.forEach{
                    listOfIds.add(it)
                }
            }
            UserListsEnum.DISPLAY ->
            {
                userListsDatabase.onDisplay.forEach{
                    listOfIds.add(it)
                }
            }
        }

        return listOfIds
    }

    fun getItemsInList(idList:ArrayList<Int>): ArrayList<GunplaItem>{

        val itemList = arrayListOf<GunplaItem>()
        idList.forEach { it: Int ->
            itemList.add(DB.gunplaDatabase[it])
        }
        return itemList
    }

    fun clearItemFromList(itemId: Int,listName: UserListsEnum = currentList){

        when(listName){
            UserListsEnum.WANTED -> if(userListsDatabase.wanted.contains(itemId)) userListsDatabase.wanted.remove(itemId)
            UserListsEnum.BACKLOG -> if(userListsDatabase.backlog.contains(itemId)) userListsDatabase.backlog.remove(itemId)
            UserListsEnum.STARTED -> if (userListsDatabase.started.contains(itemId)) userListsDatabase.started.remove(itemId)
            UserListsEnum.ASSEMBLED -> if( userListsDatabase.assembled.contains(itemId)) userListsDatabase.assembled.remove(itemId)
            UserListsEnum.CUSTOM -> if(!userListsDatabase.customizing.contains(itemId)) userListsDatabase.customizing.remove(itemId)
            UserListsEnum.FINISHED -> if (userListsDatabase.finished.contains(itemId)) userListsDatabase.finished.remove(itemId)
            UserListsEnum.DISPLAY -> if ( userListsDatabase.onDisplay.contains(itemId)) userListsDatabase.onDisplay.remove(itemId)
        }
    }
    fun addItemToList(itemId: Int,listName: UserListsEnum = currentList){

        when(listName){
            UserListsEnum.WANTED -> if (!userListsDatabase.wanted.contains(itemId)) userListsDatabase.wanted.add(itemId)
            UserListsEnum.BACKLOG -> if (!userListsDatabase.backlog.contains(itemId)) userListsDatabase.backlog.add(itemId)
            UserListsEnum.STARTED -> if (!userListsDatabase.started.contains(itemId)) userListsDatabase.started.add(itemId)
            UserListsEnum.ASSEMBLED -> if(!userListsDatabase.assembled.contains(itemId)) userListsDatabase.assembled.add(itemId)
            UserListsEnum.CUSTOM -> if(!userListsDatabase.customizing.contains(itemId)) userListsDatabase.customizing.add(itemId)
            UserListsEnum.FINISHED -> if (!userListsDatabase.finished.contains(itemId)) userListsDatabase.finished.add(itemId)
            UserListsEnum.DISPLAY -> if (!userListsDatabase.onDisplay.contains(itemId)) userListsDatabase.onDisplay.add(itemId)
        }
    }

    fun manageClickOnItem(listName: UserListsEnum, gunplaId: Int)
    {
        when (listName){
            UserListsEnum.WANTED -> {
                if(userListsDatabase.wanted.contains(gunplaId))
                {
                    userListsDatabase.wanted.remove(gunplaId)
                }
                else{
                    userListsDatabase.wanted.add(gunplaId)
                }

            }
            UserListsEnum.BACKLOG -> {
                if(userListsDatabase.backlog.contains(gunplaId))
                {
                    userListsDatabase.backlog.remove(gunplaId)
                }
                else{
                    userListsDatabase.backlog.add(gunplaId)
                }
            }
            UserListsEnum.STARTED -> {
                if(userListsDatabase.started.contains(gunplaId))
                {
                    userListsDatabase.started.remove(gunplaId)
                }
                else{
                    userListsDatabase.started.add(gunplaId)
                }
            }
            UserListsEnum.ASSEMBLED -> {
                if(userListsDatabase.assembled.contains(gunplaId))
                {
                    userListsDatabase.assembled.remove(gunplaId)
                }
                else{
                    userListsDatabase.assembled.add(gunplaId)
                }
            }
            UserListsEnum.CUSTOM -> {
                if(userListsDatabase.customizing.contains(gunplaId))
                {
                    userListsDatabase.customizing.remove(gunplaId)
                }
                else{
                    userListsDatabase.customizing.add(gunplaId)
                }
            }
            UserListsEnum.FINISHED -> {
                if(userListsDatabase.finished.contains(gunplaId))
                {
                    userListsDatabase.finished.remove(gunplaId)
                }
                else{
                    userListsDatabase.finished.add(gunplaId)
                }
            }
            UserListsEnum.DISPLAY -> {
                if(userListsDatabase.onDisplay.contains(gunplaId))
                {
                    userListsDatabase.onDisplay.remove(gunplaId)
                }
                else{
                    userListsDatabase.onDisplay.add(gunplaId)
                }
            }
        }

    }

    //var gunplaList = gunplaDatabase.gunplaDatabase

    fun clearUserLists(activity: Activity){
        val prefsEditor = activity.getSharedPreferences(activity.getString((R.string.myLists)), Context.MODE_PRIVATE)?: return

        val editor = prefsEditor.edit()
        editor.clear().apply()

        readUserLists(activity)
    }

    fun writeUserLists(activity: Activity)
    {
        val prefsEditor = activity.getSharedPreferences(activity.getString(R.string.myLists), Context.MODE_PRIVATE)?: return

        val gson = Gson()
        val jsonString = gson.toJson(userListsDatabase).toString()

        val editor: Editor =  prefsEditor.edit()

        editor.putString( "aaa", jsonString)
            .apply()


        /*val a = ""
        prefsEditor.getString(activity.getString(R.string.myLists), a)?: return
        */


    }


    fun readUserLists (activity: Activity) {

        val prefsEditor =  activity.getSharedPreferences(activity.getString(R.string.myLists), Context.MODE_PRIVATE)?: return

        val ss = prefsEditor.getString("aaa", "")

        val gson = Gson()

        userListsDatabase = gson.fromJson(ss, UserLists::class.java)?: UserLists()
    }

    /*
    fun getResourceAsText(activity: Activity): String {
        val jsonData = context.resources.openRawResource(
            context.resources.getIdentifier(
                "gunpla_checklist_json_database",
                "raw", context.packageName
            )
        ).bufferedReader().use { it.readText() }
        return jsonData
    }*/
}