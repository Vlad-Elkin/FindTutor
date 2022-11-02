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
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.repository.TutorRepository
import com.example.findtutor.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentProfileBinding.inflate(inflater,container,false )
        val root:View = binding.root
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        TutorRepository(requireContext()).subjectList.observe(viewLifecycleOwner){
            if (arguments?.get("user")!= null)
                viewModel.setProfile(arguments?.get("user") as User,it)
            else
                viewModel.setProfile(arguments?.get("tutor") as User,it,true)
        }
        binding.profile =viewModel
        binding.profileBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.vkBtn.setOnClickListener {
            viewModel.vk.value.let { link ->
                if (!link.isNullOrBlank()){
                    val uri = Uri.parse("http://vk.com/$link")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                }else{
                    Log.d("ProfileFragment","Не получается перейти")
                }
            }
        }
        binding.btnAction.setOnClickListener {
            if (viewModel.isTutorProfile){
                viewModel.phone.value.let { phone ->
                    if (!phone.isNullOrBlank()){
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phone")
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

        return root
    }
}