package com.example.androiduicomponents.molecules.models

import android.graphics.drawable.Drawable

data class PicassoImageContent(
    val imageUrl: String?,
    val placeHolder: Drawable? = null,
    var callback:ImageLoadingCallback? = null
)

interface ImageLoadingCallback{
    fun onSuccess()
    fun onError()
}
