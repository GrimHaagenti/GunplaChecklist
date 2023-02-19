package com.example.listas.dataclasses

import com.google.firebase.database.PropertyName

data class GunplaDatabase (
    @get:PropertyName("DataFormat")
    @set:PropertyName("DataFormat") var DataFormat: String = "",

    @PropertyName("FormatVersion")
    var FormatVersion: Double = 0.0,

    @PropertyName("gunplaDatabase")
    var gunplaDatabase: List<GunplaItem> = listOf()
){

}