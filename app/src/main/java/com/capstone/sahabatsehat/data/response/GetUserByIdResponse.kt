package com.capstone.sahabatsehat.data.response

import com.google.gson.annotations.SerializedName

data class GetUserByIdResponse(

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("nohp")
	val nohp: String,

	@field:SerializedName("isOnline")
	val isOnline: Boolean,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("avatar")
	val avatar: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
