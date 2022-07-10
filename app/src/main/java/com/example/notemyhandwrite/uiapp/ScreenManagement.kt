package com.example.notemyhandwrite.uiapp

import android.graphics.Bitmap
import android.net.Uri

object ScreenManagement : IScreenManagement {

    private var chooseInputMethodScreen : ChooseInputMethod? = null

    fun setChooseInputMethodScreen(screen: ChooseInputMethod){
        chooseInputMethodScreen = screen
    }

    override fun setImageInChooseInputScreen(image: Bitmap) {
        //TODO do poprawy xd - bardziej kotlinowa składnia
        if (chooseInputMethodScreen != null){
            chooseInputMethodScreen?.setImage(image)
        }

    }

    override fun setImageInChooseInputScreen(image: Uri) {
        if (chooseInputMethodScreen != null){
            chooseInputMethodScreen?.setImage(image)
        }
    }
}