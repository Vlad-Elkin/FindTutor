package com.example.findtutor.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.findtutor.R
import com.example.findtutor.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding =FragmentLoginBinding.inflate(inflater,container,false)
        loginViewModel.repository.tutorList.observe(viewLifecycleOwner){}
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginBtn.setOnClickListener {
            val login = binding.loginEmailPhone.text.toString()
            val password = binding.loginPassword.text.toString()
            val tutor = loginViewModel.checkTutor(login,password)
            if (tutor!= null){
                val bundle = bundleOf("user" to tutor)
                findNavController().navigate(R.id.LoginToMaps, bundle)
            }
            else{
                Toast.makeText(this.requireContext(),"User is not found",Toast.LENGTH_SHORT).show()
            }

        }

    }
}