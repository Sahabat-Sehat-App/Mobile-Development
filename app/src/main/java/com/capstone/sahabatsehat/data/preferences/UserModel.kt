package com.capstone.sahabatsehat.data.preferences

data class UserModel (
    val name: String,
    val nohp: String,
    val isOnline: Boolean,
    val accessToken: String,
    val userId: String,
    val email: String,
    val alamat: String? = null
)