package com.example.notemyhandwrite.app

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.FileProvider
import com.example.notemyhandwrite.uiapp.IScreenManagment
import com.example.notemyhandwrite.uiapp.ScreenManagment
import java.io.File
import java.io.IOException

object EventHandler : IHandler {

    private val screenManagment: IScreenManagment = ScreenManagment

    //Request codes
    private const val IMAGE_CAMERA = 1
    private const val IMAGE_GALLERY = 2

    //Intents
    private val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    private val intentGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

    //Other
    private var imageBitmap: Bitmap? = null
    private var imageUri: Uri? = null

    override fun pickImageFromCamera(activity: Activity) {

        val imageFile: File? = try {
            EventHandlerHelper.createImageFile(activity)
        } catch (ex: IOException) {
            null
        }

        imageFile?.also {
            imageUri = FileProvider.getUriForFile(activity, "com.example.android.fileprovider", imageFile)
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }

        try {
            startActivityForResult(activity, intentCamera, IMAGE_CAMERA, null)
        } catch (e: ActivityNotFoundException) {
            //Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            //TODO dodać wywświetlenie Tosta w obsłudze ekranu
        }
    }

    override fun pickImageFromGallery(activity: Activity) {
        try {
            startActivityForResult(activity, intentGallery, IMAGE_GALLERY, null)
        } catch (e: ActivityNotFoundException) {
            //Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_SHORT).show()
            //TODO dodać wywświetlenie Tosta w obsłudze ekranu
        }
    }

    override fun activityResultHandler(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IMAGE_CAMERA && resultCode == Activity.RESULT_OK) {
            //imageBitmap = data?.extras?.get("data") as Bitmap
            screenManagment.setImageInChooseInputScreen(imageUri!!)
        }
        if (requestCode == IMAGE_GALLERY && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            screenManagment.setImageInChooseInputScreen(imageUri!!)
        }

    }
}