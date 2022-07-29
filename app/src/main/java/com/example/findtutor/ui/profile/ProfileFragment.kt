package com.example.findtutor.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findtutor.data.entities.Subject
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.local.ExampleData
import com.example.findtutor.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding =FragmentProfileBinding.inflate(inflater,container,false )
        val root:View = binding.root
        binding.profile =viewModel
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}