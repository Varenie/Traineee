package com.varenie.traineee.ui.ExerciseParametersScreen

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap

data class ParameterContent(
    val id: Int,
    val name: String,
    val type: ParameterType,
    val options: List<String>? = null,
    val icon: ImageBitmap? = null
)
