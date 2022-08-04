package com.example.findtutor.ui.register

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.findtutor.LocationTrackingService
import com.example.findtutor.R
import com.example.findtutor.databinding.FragmentRegisterBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.maps.model.LatLng

import java.io.ByteArrayOutputStream
import java.io.IOException


class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }
    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel
    private var photo:ByteArray? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        _binding = FragmentRegisterBinding.inflate(inflater,container,false )
        val root:View = binding.root
        photo = ByteArrayOutputStream().let {
            val drawable = binding.regImage.drawable
            val bitmap = (drawable as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.PNG,100,it)
            it.toByteArray()
        }
        binding.regImage.setOnClickListener {
            getPhoto()
            if(photo == null){
                photo = ByteArrayOutputStream().let {
                    val drawable = context?.getDrawable(R.drawable.avatar)
                    val bitmap = (drawable as BitmapDrawable).bitmap
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,it)
                    it.toByteArray()
                }
            }
            binding.regImage.setImageBitmap(photo?.let { ph -> BitmapFactory.decodeByteArray(ph,0, ph.size) })
        }

        binding.registerBtn.setOnClickListener {

            viewModel.insertNewUser(
                binding.switch1.isActivated,
                photo!!,
                binding.regName.text.toString(),
                binding.regSurname.text.toString(),
                binding.regEmail.text.toString(),
                binding.regPhone.text.toString(),
                binding.regPassword.text.toString(),
                binding.regLinkVK.text.toString(),
                binding.regSubjectSpinner.selectedItemPosition+1,
                binding.regExp.text.toString().toDouble(),
                binding.regAboutSelf.text.toString(),
                getPosition())

            findNavController().navigate(R.id.RegisterToStart)
        }


        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            photo = readBytes(context!!, uri)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


    fun getPhoto(){
        ImagePicker.with(this)
            .crop()                 //Crop image(Optional), Check Customization for more option
            .start()
    }

    fun getPosition(): LatLng {
        val location = LocationTrackingService()
        return LatLng(0.0, 0.0)
    }

    @Throws(IOException::class)
    fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}