package com.example.listas.dataclasses

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

data class UserLists(

    @SerializedName("Wanted")
    var wanted : ListItem,
    @SerializedName("Backlog")
    var backlog : ListItem,
    @SerializedName("Started")
    var started : ListItem,
    @SerializedName("Assambled")
    var assambled : ListItem,
    @SerializedName("Customizing")
    var customizing : List<ListItem>,
    @SerializedName("Done")
    var done : ListItem,
    @SerializedName("OnDisplay")
    var onDisplay : ListItem,

)

