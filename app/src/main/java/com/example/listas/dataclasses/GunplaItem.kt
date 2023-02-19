package com.example.listas.dataclasses
import com.google.firebase.database.PropertyName

data class GunplaItem (

    @get:PropertyName("_id")
    @set:PropertyName("_id") var id: Int = -1,

    @PropertyName("FullName")
    var fullName: String = "",

    @PropertyName("MsModelNumber")
    var msModelNumber: String = "",

    @PropertyName("Name")
    var name: String = "",

    @PropertyName("Scale")
    var scale: String = "",

    @PropertyName("Grade")
    var grade: String = "",

    @PropertyName("Series")
    var series: String = "",

    @PropertyName("Date")
    var date: String = "",

    @PropertyName("BoxArtURL")
    var boxArtURL: String = ""
    )
{





}