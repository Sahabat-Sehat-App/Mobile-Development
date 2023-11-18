package com.capstone.sahabatsehat.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetUserModel(
    val id: String,
    val name: String,
    val email: String,
    val nohp: String,
    val accessToken: String,
    val avatar:String
): Parcelable
