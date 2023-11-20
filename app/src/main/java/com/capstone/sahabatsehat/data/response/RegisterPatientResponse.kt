package com.capstone.sahabatsehat.data.response

import com.google.gson.annotations.SerializedName

data class RegisterPatientResponse(

	@field:SerializedName("patient")
	val patient: Patient,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class Patient(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("keluhan")
	val keluhan: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String
)
