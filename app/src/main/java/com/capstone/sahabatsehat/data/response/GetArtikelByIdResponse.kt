package com.capstone.sahabatsehat.data.response

import com.google.gson.annotations.SerializedName

data class GetArtikelByIdResponse(

	@field:SerializedName("artikel")
	val artikel: Artikel,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class Artikel(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("penulis")
	val penulis: String,

	@field:SerializedName("adminId")
	val adminId: String,

	@field:SerializedName("admin")
	val admin: Admin,

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

data class Admin(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("nohp")
	val nohp: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("alamat")
	val alamat: String? = null
)
