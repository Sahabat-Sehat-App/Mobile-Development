package com.capstone.sahabatsehat.ui.layanan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.response.LayananItem
import com.capstone.sahabatsehat.databinding.ItemDoctorBinding

class ListLayananDoctorAdapter(private var listLayanan: ArrayList<LayananItem>): RecyclerView.Adapter<ListLayananDoctorAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemDoctorBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvHarga.text = listLayanan[position].harga.toString()
            tvName.text = listLayanan[position].doctor.name
            tvSpesialis.text = listLayanan[position].doctor.spesialis
            tvPengalaman.text = listLayanan[position].doctor.pengalamankerja
            Glide.with(itemView.context).load(ApiConfig.URL_AVATAR + listLayanan[position].image).centerCrop().into(ivImage)

            btnBooking.setOnClickListener {
                onItemClickCallback.onItemClicked(listLayanan[adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listLayanan.size

    fun updateData(){
        listLayanan.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvHarga = binding.tvPrice
        val ivImage = binding.imgDoctor
        val tvName = binding.tvNameDoctor
        val tvSpesialis = binding.tvSpesialis
        val tvPengalaman = binding.tvPengalaman
        val btnBooking = binding.btnBooking
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: LayananItem)
    }
}