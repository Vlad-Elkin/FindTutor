package com.example.findtutor.ui.profile

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.*
import androidx.navigation.fragment.findNavController
import com.example.findtutor.R
import com.example.findtutor.data.entities.Tutor
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
        val isTutorProfile: Boolean?
        val tutor:Any?
        if (arguments?.get("user")!= null){
            tutor = arguments?.get("user") as Tutor
            isTutorProfile = false
        }
        else{
            tutor = arguments?.get("tutor") as Tutor
            isTutorProfile = true
        }

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.setProfile(tutor,isTutorProfile)
        binding =FragmentProfileBinding.inflate(inflater,container,false )
        val root:View = binding.root
        binding.profile =viewModel
        binding.btnAction.setOnClickListener {
            if (isTutorProfile){
                viewModel.phone.value.let { phone ->
                    if (!phone.isNullOrBlank()){
                        val dial = Uri.parse("tel:${phone}")
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = dial
                        startActivity(intent)
                    }
                    else{
                        Log.d("ProfileFragment","Нет номера телефона")
                    }
                }
            }
            else{
                findNavController().navigate(R.id.ProfileToEdit,arguments)
            }
        }
        binding.profileBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return root
    }
}