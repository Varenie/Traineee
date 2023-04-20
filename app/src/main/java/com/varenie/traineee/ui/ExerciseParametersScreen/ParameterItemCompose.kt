package com.varenie.traineee.ui.ExerciseParametersScreen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize


@Composable
fun ParameterItemCompose(item: ParameterContent) {
    when(item.type) {
        ParameterType.TEXT -> TextParameter(label = item.name)
        ParameterType.FLAG -> CheckBoxParameter(label = item.name)
        ParameterType.CHOICE -> DropdownMenuParameter(label = item.name, options = item.options!!)
        ParameterType.IMAGE -> IconParameter(icon = item.icon!!)
    }
}

@Composable
private fun TextParameter(label: String) {
    val text = remember { mutableStateOf("") }

    OutlinedTextField(
        value = text.value,
        label = { Text(label) },
        onValueChange = { text.value = it},
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 3.dp)
    )
}

@Composable
private fun CheckBoxParameter(label: String) {
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label)
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
    }
}

@Composable
private fun DropdownMenuParameter(label: String, options: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = { selectedOption = it },
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .clickable { expanded = !expanded },
            label = {Text(label)},
            trailingIcon = {
                Icon(icon, "contentDescription", Modifier.clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            options.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedOption = item
                    expanded = false
                }) {
                    Text(text = item)
                }
            }
        }
    }
}

@Composable
private fun IconParameter(icon: ImageBitmap) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 3.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            bitmap = icon,
            contentDescription = "icon",
            modifier = Modifier
//                .align(Alignment.CenterVertically)
                .height(100.dp),
//            alignment = Alignment.CenterHorizontally
        )
    }
}
