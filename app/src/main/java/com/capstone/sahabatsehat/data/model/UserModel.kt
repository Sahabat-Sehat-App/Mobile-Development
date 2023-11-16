package com.capstone.sahabatsehat.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UserModel (
    val id: String,
    val name: String,
    val email: String,
    val nohp: String,
    val isLogin: Boolean,
    val accessToken: String,
): Parcelable