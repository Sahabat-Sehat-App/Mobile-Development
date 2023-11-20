package com.capstone.sahabatsehat.data.response

import com.google.gson.annotations.SerializedName

data class SearchLayananByTypeResponse(

	@field:SerializedName("layanan")
	val layanan: List<LayananItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class LayananItem(

	@field:SerializedName("doctor")
	val doctor: Doctor,

	@field:SerializedName("image")
	val image: Any,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("harga")
	val harga: Int,

	@field:SerializedName("doctorId")
	val doctorId: String,

	@field:SerializedName("jenisLayanan")
	val jenisLayanan: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

data class Doctor(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("pengalamankerja")
	val pengalamankerja: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("nohp")
	val nohp: String,

	@field:SerializedName("spesialis")
	val spesialis: String,

	@field:SerializedName("isOnline")
	val isOnline: Boolean,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("avatar")
	val avatar: Any,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
