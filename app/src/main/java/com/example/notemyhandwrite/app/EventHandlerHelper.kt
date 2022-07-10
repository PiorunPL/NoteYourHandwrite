package com.example.notemyhandwrite.app

import android.app.Activity
import android.os.Environment
import java.io.File
import java.util.Date
import java.text.SimpleDateFormat

object EventHandlerHelper {
    public fun createImageFile(activity: Activity): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        val prefix: String = "JPEG_${timeStamp}_"
        val suffix: String = ".jpg"
        return File.createTempFile(prefix, suffix, storageDir)
    }



}