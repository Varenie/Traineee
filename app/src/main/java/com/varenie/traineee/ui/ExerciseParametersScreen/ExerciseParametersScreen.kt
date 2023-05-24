package com.varenie.traineee.ui.ExerciseParametersScreen

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun ExerciseParametersScreen() {
    val testList = listOf(
        ParameterContent(0, "icon", ParameterType.IMAGE, icon = convertToBitmap(null, LocalContext.current)),
        ParameterContent(1, "textTest", ParameterType.TEXT),
        ParameterContent(2, "flagTest", ParameterType.FLAG),
        ParameterContent(3, "optionsTest", ParameterType.CHOICE, options = listOf("test1", "test2", "test3", "test4", "test5", "test6")),
        ParameterContent(4,"exercise count", ParameterType.COUNT)
    )
    Scaffold(
        topBar = {},
        bottomBar = {},
        content = { innerPadding ->
            ParametersSection(
                parametersList = testList,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}

@Composable
private fun ParametersSection(
    parametersList: List<ParameterContent>,
    modifier: Modifier = Modifier
) {
    Column {
        parametersList.forEach {
            ParameterItemCompose(item = it)
        }
    }
}

private fun convertToBitmap(image: ByteArray?, context: Context): ImageBitmap {
    image?.let {
        return BitmapFactory.decodeByteArray(it, 0, it.size).asImageBitmap()
    }
    return BitmapFactory.decodeResource(context.resources, com.varenie.traineee.R.drawable.workout).asImageBitmap()
}
