package com.example.listas.dataclasses
import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso

data class GunplaItem (
    val _id: Int,

    val FullName: String,
    val MsModelNumber: String,
    val Name: String,
    val Scale: String,
    val Grade: String,
    val Series: String,
    val Date: String,
    val BoxArtURL: String
    )
{





}