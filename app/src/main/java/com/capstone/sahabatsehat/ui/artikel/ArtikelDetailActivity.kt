package com.capstone.sahabatsehat.ui.artikel

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.capstone.sahabatsehat.data.api.ApiConfig
import com.capstone.sahabatsehat.data.response.Artikel
import com.capstone.sahabatsehat.databinding.ActivityArtikelDetailBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.util.ViewModelFactory
import com.capstone.sahabatsehat.util.formatDate
import java.util.TimeZone

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ArtikelDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArtikelDetailBinding
    private lateinit var viewModel: ArtikelDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtikelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModelSetup()
    }

    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this,
            ViewModelFactory(UserPreference.getInstance(dataStore), this)
        )[ArtikelDetailViewModel::class.java]

        viewModel.getUser().observe(this){
            val getArtikelId = intent.getStringExtra(EXTRA_ID_ARTIKEL)
            if(getArtikelId != null){
                viewModel.getArtikelById(getArtikelId, it.accessToken)
            }
        }

        viewModel.artikelData.observe(this){artikelData ->
            if(artikelData != null){
                setArtikelData(artikelData)
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

    private fun setArtikelData(artikel: Artikel){
        binding.tvCategories.text = artikel.jenisArtikel
        binding.tvTitle.text = artikel.judul
        Glide.with(this).load(ApiConfig.URL_AVATAR + artikel.image).into(binding.ivImageArtikel)
        binding.tvDate.text = formatDate(artikel.createdAt, TimeZone.getDefault().id)
        Glide.with(this).load(ApiConfig.URL_AVATAR + artikel.admin.avatar).into(binding.ivAvatarUser)
        binding.tvNameUser.text = artikel.penulis
        binding.tvDesc.text = artikel.deskripsi


    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object{
        const val EXTRA_ID_ARTIKEL = "extra_id_news"
    }
}