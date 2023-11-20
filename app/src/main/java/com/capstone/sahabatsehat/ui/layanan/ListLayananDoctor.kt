package com.capstone.sahabatsehat.ui.layanan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.sahabatsehat.data.response.LayananItem
import com.capstone.sahabatsehat.databinding.ActivityListLayananDoctorBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.ui.artikel.ArtikelDetailActivity
import com.capstone.sahabatsehat.ui.booking.BookingActivity
import com.capstone.sahabatsehat.ui.layanan.adapter.ListLayananDoctorAdapter
import com.capstone.sahabatsehat.util.ViewModelFactory
import java.util.ArrayList

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ListLayananDoctor : AppCompatActivity() {
    private lateinit var binding: ActivityListLayananDoctorBinding
    private lateinit var viewModel: ListLayananDoctorViewModel
    private lateinit var adapter: ListLayananDoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListLayananDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelSetup()
    }

    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(dataStore), this))[ListLayananDoctorViewModel::class.java]

        viewModel.getUser().observe(this){
            val getLayananType = intent.getStringExtra(EXTRA_LAYANAN_TYPE)
            if(getLayananType != null){
                viewModel.searchLayananByType(it.accessToken, getLayananType)
                viewModel.layananData.observe(this){layananData ->
                    if(layananData.isNotEmpty()){
                        binding.rvListDoctor.visibility = View.VISIBLE
                        binding.ivNotFound.visibility = View.GONE
                        binding.tvNotFound.visibility = View.GONE
                        setLayananData(layananData)
                        Log.d("DataLayanan", layananData.toString())
                    }else{
                        binding.rvListDoctor.visibility = View.GONE
                        binding.ivNotFound.visibility = View.VISIBLE
                        binding.tvNotFound.visibility = View.VISIBLE

                    }
                }
            }
        }

        viewModel.isLoading.observe(this){
            showLoading(it)
        }

        viewModel.snackbarText.observe(this){
            it.getContentIfNotHandled()?.let { snackBarText ->
                showToast(snackBarText)
            }
        }


    }

    private fun setLayananData(service: List<LayananItem>){
        val listService = ArrayList<LayananItem>()
        adapter = ListLayananDoctorAdapter(listService)
        adapter.updateData()

        for(services in service){
            listService.add(services)
        }

        listService.sortByDescending {
            it.createdAt
        }

        adapter.setOnItemClickCallback(object: ListLayananDoctorAdapter.OnItemClickCallback{
            override fun onItemClicked(data: LayananItem) {
                val intent = Intent(this@ListLayananDoctor, BookingActivity::class.java)
                intent.putExtra(BookingActivity.EXTRA_LAYANAN_ID, data.id)
                intent.putExtra(BookingActivity.EXTRA_DOCTOR_ID, data.doctorId)

                startActivity(intent)
            }
        })

        binding.rvListDoctor.adapter = adapter
        binding.rvListDoctor.layoutManager = LinearLayoutManager(this)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    companion object{
        const val EXTRA_LAYANAN_TYPE = "extra_layanan_type"
    }
}