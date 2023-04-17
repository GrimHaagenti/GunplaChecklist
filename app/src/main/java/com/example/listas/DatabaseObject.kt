package com.example.listas
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



object DatabaseObject {

    lateinit var DB: GunplaDatabase

    lateinit var userListsDatabase: UserLists

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

        //try gettin a Json object and parse it
        var u = UserLists()

        return u
    }

    fun initUserLists(){

        //Try getting the user lists
        userListsDatabase = getLists();


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
                //userListsDatabase.customizing.add(gunplaId);
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


    /*fun ReadJson (): GunplaDatabase {
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
    }*/
}