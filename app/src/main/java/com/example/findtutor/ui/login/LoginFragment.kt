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

    companion object {
        fun newInstance() = LoginFragment()
    }
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        _binding =FragmentLoginBinding.inflate(inflater,container,false )
        val root:View = binding.root

        binding.loginBtn.setOnClickListener {
            val login = binding.loginEmailPhone.text.toString()
            val password = binding.loginPassword.text.toString()
            val tutor = viewModel.checkTutor(login,password)
            if (tutor!= null){
                var bundle = if (tutor.isTutor){
                    bundleOf("tutor" to tutor)
                } else{
                    bundleOf("user" to tutor)
                }
                findNavController().navigate(R.id.LoginToMaps, bundle)
            }
            else{
                Toast.makeText(this.requireContext(),"User is not found",Toast.LENGTH_SHORT).show()
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}