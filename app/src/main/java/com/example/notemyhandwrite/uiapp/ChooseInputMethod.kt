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
import com.example.notemyhandwrite.databinding.ChooseInputMethodBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ChooseInputMethod : Fragment() {

    private var _binding: ChooseInputMethodBinding? = null
    private val binding get() = _binding!!

    private val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    private val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

    private var imageBitmap : Bitmap? = null
    private var imageUri : Uri? = null
    private val REQUEST_IMAGE_CAPTURE = 1
    private val PICK_IMAGE = 2

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
            try {
                    startActivityForResult(intentGallery, PICK_IMAGE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonCamera.setOnClickListener {
            try {
                startActivityForResult(intentCamera, REQUEST_IMAGE_CAPTURE)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            binding.testowy.setImageBitmap(imageBitmap)
        }
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data?.data
            binding.testowy.setImageURI(imageUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}