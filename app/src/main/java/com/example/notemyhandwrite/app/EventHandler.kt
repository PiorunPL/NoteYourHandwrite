package com.example.notemyhandwrite.app

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.notemyhandwrite.uiapp.IScreenManagment
import com.example.notemyhandwrite.uiapp.ScreenManagment

object EventHandler : IHandler{

    private val screenManagment : IScreenManagment = ScreenManagment

    //Request codes
    private val REQUEST_IMAGE_CAPTURE = 1
    private val PICK_IMAGE = 2

    //Intents
    private val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    private val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

    //Other
    private var imageBitmap : Bitmap? = null
    private var imageUri    : Uri? = null

    override fun pickImageFromCamera(activity: Activity) {
        try {
            startActivityForResult(activity, intentCamera, REQUEST_IMAGE_CAPTURE,null)
        } catch (e: ActivityNotFoundException) {
            //Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            //TODO dodać wywświetlenie Tosta w obsłudze ekranu
        }
    }

    override fun pickImageFromGallery(activity: Activity) {
        try {
            startActivityForResult(activity,intentGallery, PICK_IMAGE, null)
        } catch (e: ActivityNotFoundException) {
            //Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            //TODO dodać wywświetlenie Tosta w obsłudze ekranu
        }
    }

    override fun activityResultHandler(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            screenManagment.setImageInChooseInputScreen(imageBitmap!!)
        }
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            screenManagment.setImageInChooseInputScreen(imageUri!!)
        }

    }
}