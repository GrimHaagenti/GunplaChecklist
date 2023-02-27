package com.example.listas.dataclasses

import com.google.gson.annotations.SerializedName

data class ListItem(

    @SerializedName("name")
    var name : String = "",

    @SerializedName("mainList")
    var mainList : List<GunplaItem> = listOf()

)
