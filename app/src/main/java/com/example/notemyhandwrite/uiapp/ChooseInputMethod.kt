package com.example.notemyhandwrite.uiapp

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    fun setImage(image: Bitmap){
        binding.testowy.setImageBitmap(image)
    }

    fun setImage(image: Uri){
        binding.testowy.setImageURI(image)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScreenManagement.setChooseInputMethodScreen(this)
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