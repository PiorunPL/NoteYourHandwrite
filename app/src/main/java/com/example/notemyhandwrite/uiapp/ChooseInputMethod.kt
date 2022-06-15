package com.example.notemyhandwrite.uiapp

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notemyhandwrite.app.EventHandler
import com.example.notemyhandwrite.app.IHandler
import com.example.notemyhandwrite.databinding.ChooseInputMethodBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ChooseInputMethod : Fragment() {

    private var _binding: ChooseInputMethodBinding? = null
    private val binding get() = _binding!!

    private val eventHandler : IHandler = EventHandler // Jaka jest zaleta używania w tym miejscu interfejsu?

    private val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    private val PICK_IMAGE = 2

    private var imageBitmap : Bitmap? = null
    private var imageUri : Uri? = null


    fun setImage(image: Bitmap){
        binding.testowy.setImageBitmap(image)
    }

    fun setImage(image: Uri){
        binding.testowy.setImageURI(image)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenManagment.setChooseInputMethodScreen(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ChooseInputMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGallery.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            eventHandler.pickImageFromGallery(requireActivity())
        }

        binding.buttonCamera.setOnClickListener {
            eventHandler.pickImageFromCamera(requireActivity())
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}