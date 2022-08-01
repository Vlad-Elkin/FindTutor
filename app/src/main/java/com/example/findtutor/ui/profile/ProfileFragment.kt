package com.example.findtutor.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findtutor.data.entities.Subject
import com.example.findtutor.data.entities.Tutor
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.local.ExampleData
import com.example.findtutor.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var binding: FragmentProfileBinding

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tutor = arguments?.get("tutor") as Tutor
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.setProfile(tutor)
        binding =FragmentProfileBinding.inflate(inflater,container,false )
        val root:View = binding.root
        binding.profile =viewModel
        return root
    }
}