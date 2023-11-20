package com.capstone.sahabatsehat.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.sahabatsehat.R
import com.capstone.sahabatsehat.data.response.ArtikelItem
import com.capstone.sahabatsehat.data.response.GetUserByIdResponse
import com.capstone.sahabatsehat.databinding.FragmentHomeBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.ui.artikel.ArtikelDetailActivity
import com.capstone.sahabatsehat.ui.home.adapter.ListArtikelAdapter
import com.capstone.sahabatsehat.ui.layanan.ListLayananDoctor
import com.capstone.sahabatsehat.util.ViewModelFactory
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import java.util.ArrayList

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var adapter: ListArtikelAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        bannerCarousel()
        viewModelSetup()
        buttonListener()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this, ViewModelFactory(UserPreference.getInstance(requireActivity().dataStore), requireContext()))[HomeFragmentViewModel::class.java]
        val lightSeaGrean = ContextCompat.getColor(requireContext(), R.color.lightSeaGreen)

        viewModel.getUser().observe(viewLifecycleOwner){user ->
            viewModel.getUserById(user.id, user.accessToken)
            binding.btnSemua.setBackgroundColor(lightSeaGrean)
            binding.btnSehat.setBackgroundColor(Color.WHITE)
            binding.btnSehat.setTextColor(Color.BLACK)

            viewModel.getAllNews(user.accessToken)

            binding.btnSemua.setOnClickListener {
                binding.btnSemua.setBackgroundColor(lightSeaGrean)
                binding.btnSehat.setBackgroundColor(Color.WHITE)
                binding.btnSehat.setTextColor(Color.BLACK)
                binding.btnSemua.setTextColor(Color.WHITE)


                viewModel.getAllNews(user.accessToken)
            }

            binding.btnSehat.setOnClickListener {
                binding.btnSehat.setBackgroundColor(lightSeaGrean)
                binding.btnSemua.setBackgroundColor(Color.WHITE)
                binding.btnSemua.setTextColor(Color.BLACK)
                binding.btnSehat.setTextColor(Color.WHITE)

                viewModel.searchArtikelByType(user.accessToken, "kesehatan")
            }
        }

        viewModel.userData.observe(viewLifecycleOwner){
            setUSerData(it)
        }

        viewModel.artikelData.observe(viewLifecycleOwner){artikelData ->
            if(artikelData != null){
                setArtikelData(artikelData)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        viewModel.snackbarText.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { snackBarText ->
                showToast(snackBarText)
            }
        }

    }

    private fun setUSerData(user: GetUserByIdResponse){
        binding.tvName.text = "Hai, ${user.user.name}"

    }

    private fun setArtikelData(artikel: List<ArtikelItem>){
        val listArtikel = ArrayList<ArtikelItem>()
        adapter = ListArtikelAdapter(listArtikel)
        adapter.updateData()

        for(info in artikel){
            listArtikel.add(info)
        }

        listArtikel.sortByDescending {
            it.createdAt
        }

        adapter.setOnItemClickCallback(object: ListArtikelAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ArtikelItem) {
                val intent = Intent(activity, ArtikelDetailActivity::class.java)
                intent.putExtra(ArtikelDetailActivity.EXTRA_ID_ARTIKEL, data.id)
                startActivity(intent)
            }
        })

        binding.rvArtikel.adapter = adapter
        showRecyclerList(binding.rvArtikel, LinearLayoutManager.VERTICAL)
    }

    private fun showRecyclerList(rv: RecyclerView, orientation: Int){
        rv.layoutManager = LinearLayoutManager(context, orientation, false )
    }

    private fun buttonListener(){
        binding.cvSwap.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Swaptest & PCR")
            startActivity(intent)
        }

        binding.cvInjeksi.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Injeksi Vitamin")
            startActivity(intent)
        }

        binding.cvKunjungan.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Pemeriksaan Kesehatan")
            startActivity(intent)
        }

        binding.cvInfus.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Infus Dirumah")
            startActivity(intent)
        }

        binding.cvLansia.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Perawatan Lansia")
            startActivity(intent)
        }

        binding.cvLab.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Tes Laboratorium")
            startActivity(intent)
        }

        binding.cvKateter.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Pemasangan Kateter")
            startActivity(intent)
        }

        binding.cvAkupuntur.setOnClickListener {
            val intent = Intent(activity, ListLayananDoctor::class.java)
            intent.putExtra(ListLayananDoctor.EXTRA_LAYANAN_TYPE, "Fisioterapi & Akupuntur")
            startActivity(intent)
        }
    }

    private fun bannerCarousel() {
        val slideImages = ArrayList<SlideModel>()
        //Sample data
        slideImages.add(SlideModel(R.drawable.banner1))
        slideImages.add(SlideModel(R.drawable.banner3))

        binding.imageSlider.setImageList(slideImages, ScaleTypes.FIT)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }
}