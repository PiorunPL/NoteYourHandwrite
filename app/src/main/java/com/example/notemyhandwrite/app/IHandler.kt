package com.example.notemyhandwrite.app

import android.app.Activity
import android.content.Intent

interface IHandler {
    fun pickImageFromCamera(activity: Activity)
    fun pickImageFromGallery(activity: Activity)
    fun activityResultHandler(requestCode: Int, resultCode: Int, data: Intent?)
}