package com.example.findtutor.ui.edit

import android.app.Activity
import android.content.*
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.findtutor.R
import com.example.findtutor.data.entities.Tutor
import com.example.findtutor.data.repository.TutorRepository
import com.example.findtutor.databinding.FragmentEditBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import java.io.*
import java.lang.NumberFormatException

class EditFragment : Fragment() {

    private lateinit var viewModel: EditViewModel
    private lateinit var binding: FragmentEditBinding

    private var photo:ByteArray? = null

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
            binding.regImage.setImageBitmap(photo?.let { ph ->
                BitmapFactory.decodeByteArray(ph,0, ph.size) })
        }
        binding.editBtn.setOnClickListener {
            try {
                TutorRepository(requireContext()).insertUser(viewModel.user)
                findNavController().navigate(R.id.EditToProfile,
                    bundleOf("user" to viewModel.user))
            }catch (e: NumberFormatException){
                Toast.makeText(requireContext(),"Не все поля заполнены",Toast.LENGTH_SHORT).show()
            }

        }
        return root
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                val uri: Uri = data?.data!!
                photo = readBytes(context!!, uri)
            }
            ImagePicker.RESULT_ERROR -> Toast.makeText(context, ImagePicker.getError(data),
                Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    @Throws(IOException::class)
    fun readBytes(context: Context, uri: Uri): ByteArray? =
        context.contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }

    fun getPhoto(){
        ImagePicker.with(this).crop().start()
    }
}