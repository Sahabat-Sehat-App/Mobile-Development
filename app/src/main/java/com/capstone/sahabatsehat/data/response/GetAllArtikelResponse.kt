package com.capstone.sahabatsehat.data.response

import com.google.gson.annotations.SerializedName

data class GetAllArtikelResponse(

	@field:SerializedName("artikel")
	val artikel: List<ArtikelItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class ArtikelItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("penulis")
	val penulis: String,

	@field:SerializedName("adminId")
	val adminId: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String,

	@field:SerializedName("judul")
	val judul: String,

	@field:SerializedName("jenisArtikel")
	val jenisArtikel: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
