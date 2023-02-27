package com.example.listas

import com.example.listas.dataclasses.GunplaDatabase
import com.example.listas.dataclasses.GunplaItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

object DatabaseObject {

    lateinit var DB: GunplaDatabase
    var DB_Initialized = false
    private var database: DatabaseReference =
        Firebase.database("https://gunplachecklist-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("")



    fun PrintDataDebugGunpla(){
        getDatabaseInfo()


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