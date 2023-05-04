package com.example.listas.dataclasses

import com.google.gson.annotations.SerializedName

data class UserLists(

    @SerializedName("Wanted")
    var wanted : ArrayList<Int> = arrayListOf(),
    @SerializedName("Backlog")
    var backlog : ArrayList<Int> = arrayListOf(),
    @SerializedName("Started")
    var started : ArrayList<Int> = arrayListOf(),
    @SerializedName("Assambled")
    var assembled : ArrayList<Int> = arrayListOf(),
    @SerializedName("Customizing")
    var customizing : ArrayList<Int> = arrayListOf(),
    @SerializedName("Done")
    var finished : ArrayList<Int> = arrayListOf(),
    @SerializedName("OnDisplay")
    var onDisplay : ArrayList<Int> = arrayListOf()

    )

