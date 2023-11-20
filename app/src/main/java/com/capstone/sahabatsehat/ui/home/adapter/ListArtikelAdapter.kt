package com.capstone.sahabatsehat.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.response.ArtikelItem
import com.capstone.sahabatsehat.databinding.ItemArtikelBinding
import com.capstone.sahabatsehat.util.formatDate
import java.util.TimeZone

class ListArtikelAdapter(private var listArtikel: ArrayList<ArtikelItem>): RecyclerView.Adapter<ListArtikelAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemArtikelBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvCategories.text = listArtikel[position].jenisArtikel
            tvTitle.text = listArtikel[position].judul
            tvDate.text = formatDate(listArtikel[position].createdAt, TimeZone.getDefault().id)
            Glide.with(itemView.context).load(ApiConfig.URL_AVATAR + listArtikel[position].image).centerCrop().into(ivImage)

            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listArtikel[adapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = listArtikel.size

    class ViewHolder(binding: ItemArtikelBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvCategories = binding.tvCategories
        val ivImage = binding.imgArtikel
        val tvTitle = binding.tvTitle
        val tvDate = binding.tvDate
    }

    fun updateData(){
        listArtikel.clear()
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ArtikelItem)
    }
}