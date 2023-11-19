package com.capstone.sahabatsehat.ui.booking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.sahabatsehat.databinding.ItemDoctorBinding

class BookingAdapter(private var booking: List<Booking>): RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingViewHolder {
        return BookingViewHolder(ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = booking.size

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = booking[position]
        holder.bind(booking)
    }

    class BookingViewHolder(private var binding: ItemDoctorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(booking: Booking) {
            binding.tvNameDoctor.text = booking.name
            binding.tvSpesialis.text = booking.type
            binding.tvPercent.text = "${booking.rating}%"
            binding.tvPrice.text = "Rp. ${booking.price}"
            binding.tvPengalaman.text = "${booking.experience} Tahun"
        }
    }
}