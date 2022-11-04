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
import com.example.findtutor.data.entities.Subject
import com.example.findtutor.data.entities.User
import com.example.findtutor.data.repository.TutorRepository
import com.example.findtutor.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var viewModel: ProfileViewModel
    lateinit var profile:User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =FragmentProfileBinding.inflate(inflater,container,false )
        val root:View = binding.root
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profile =viewModel
        profile = (arguments?.get("user")?:arguments?.get("tutor")) as User
        TutorRepository(requireContext()).subjectList.observe(viewLifecycleOwner){
            if (arguments?.get("user")!=null){
                viewModel.setProfile(profile,it,false)
            }else{
                viewModel.setProfile(profile,it,true)
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileBack.setOnClickListener { findNavController().popBackStack() }
        binding.vkBtn.setOnClickListener {
            viewModel.vk.value.let { link ->
                if (!link.isNullOrBlank()){
                    val uri = Uri.parse("http://vk.com/$link")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
                else Log.d("ProfileFragment","Не получается перейти")
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
                    else Log.d("ProfileFragment","Нет номера телефона")
                }
            }
            else findNavController().navigate(R.id.ProfileToEdit,arguments)
        }
    }

}