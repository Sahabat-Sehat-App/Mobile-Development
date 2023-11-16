package com.capstone.sahabatsehat.ui.profile

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.sahabatsehat.databinding.FragmentProfileBinding
import com.capstone.sahabatsehat.preferences.UserPreference
import com.capstone.sahabatsehat.ui.login.LoginActivity
import com.capstone.sahabatsehat.util.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModelSetup()
        buttonListener()



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewModelSetup(){
        viewModel = ViewModelProvider(this,
            ViewModelFactory(
                UserPreference.getInstance(requireActivity().dataStore),
                requireContext()
            )
        )[ProfileViewModel::class.java]

        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

        viewModel.snackbarText.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { snackBarText ->
                showToast(snackBarText)
            }
        }
    }

    private fun buttonListener(){
        binding.logout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())

            with(builder)
            {
                setTitle("Konfirmasi Logout")
                setMessage("Apakah yakin ingin logout ?")
                setPositiveButton("Yes") { _, _ ->

                    viewModel.getUser().observe(viewLifecycleOwner){
                        viewModel.logoutUserById(it.id, it.accessToken)
                    }
                    viewModel.logout()

                    requireActivity().run {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
                setNegativeButton("No", null)
            }
            val alert = builder.create()
            alert.show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

}