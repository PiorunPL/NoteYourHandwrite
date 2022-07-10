package com.example.notemyhandwrite.uiapp

import android.graphics.Bitmap
import android.net.Uri

interface IScreenManagement {
    fun setImageInChooseInputScreen(image: Bitmap)
    fun setImageInChooseInputScreen(image: Uri)
}