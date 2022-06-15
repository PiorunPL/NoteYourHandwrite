package com.example.notemyhandwrite.uiapp

import android.graphics.Bitmap
import android.net.Uri

interface IScreenManagment {
    fun setImageInChooseInputScreen(image: Bitmap)
    fun setImageInChooseInputScreen(image: Uri)
}