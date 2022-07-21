package com.example.findtutor.ui.start

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.findtutor.R
import com.example.findtutor.data.local.ExampleData
import com.example.findtutor.databinding.FragmentRegisterBinding
import com.example.findtutor.databinding.FragmentStartBinding
import com.example.findtutor.ui.maps.MapsViewModel

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: StartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        _binding = FragmentStartBinding.inflate(inflater,container,false )
        val root:View = binding.root
        binding.btnAuth.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
        binding.btnReg.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_registerFragment)
        }
        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_aboutFragment)
        }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}