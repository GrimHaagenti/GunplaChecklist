package com.example.listas.dataclasses

import com.google.gson.annotations.SerializedName

data class CustomListItem(

    @SerializedName("name")
    var name : String = "",

    @SerializedName("mainList")
    var idList : List<Int> = listOf()

)
