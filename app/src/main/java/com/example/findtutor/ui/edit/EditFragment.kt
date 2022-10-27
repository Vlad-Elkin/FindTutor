package com.example.findtutor.ui.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.findtutor.R
import com.example.findtutor.data.entities.Tutor
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.repository.TutorRepository
import com.example.findtutor.databinding.FragmentEditBinding
import com.example.findtutor.databinding.FragmentProfileBinding

class EditFragment : Fragment() {

    private lateinit var viewModel: EditViewModel
    private lateinit var binding: FragmentEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentEditBinding.inflate(inflater,container,false )
        val root:View = binding.root
        viewModel = ViewModelProvider(this).get(EditViewModel::class.java)
        val user = arguments?.get("user") as Tutor
        viewModel.user = user.toUser()
        binding.user = viewModel
        binding.editBtn.setOnClickListener {
            //Log.d("subject",viewModel.user.id_fk_subject.toString())
            TutorRepository(requireContext()).insertUser(viewModel.user)
            bundleOf("user" to viewModel.user)
            findNavController().popBackStack()
        }
        return root
    }

}