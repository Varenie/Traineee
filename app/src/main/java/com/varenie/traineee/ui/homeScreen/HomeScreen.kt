package com.varenie.traineee.ui.homeScreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.varenie.traineee.R
import com.varenie.traineee.ui.theme.AquaBlue
import com.varenie.traineee.ui.theme.ButtonBlue
import com.varenie.traineee.ui.theme.DeepBlue

@Preview
@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ) {
        WorkoutsSection(workoutList = listOf(
            WorkoutContent("Ноги", 7),
            WorkoutContent("Грудь+бицепс", 6),
            WorkoutContent("Спина+плечи", 9)
        ), modifier = Modifier.align(Alignment.TopCenter))
        BottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_home),
            BottomMenuContent("Profile", R.drawable.ic_profile),
            BottomMenuContent("Settings", R.drawable.ic_settings)
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember { mutableStateOf(initialSelectedItemIndex) }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { 
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }
}

@Composable
fun WorkoutsSection(
    workoutList: List<WorkoutContent>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(workoutList.size) {
            WorkoutItem(workoutList[it])
        }
    }
}

//  потом можно сделать выпадающий список с предпоказом тренировки
@Composable
fun WorkoutItem(
    item: WorkoutContent
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(6.dp)
            .aspectRatio(6f)
            .clip(RoundedCornerShape(10.dp))
            .background(ButtonBlue.copy(alpha = 0.5F))
//        modifier = Modifier
//            .padding(7.5.dp)
////            .aspectRatio(11f)
//            .clip(RoundedCornerShape(10.dp))
//            .background(ButtonBlue.copy(alpha = 0.5F))
    ) {
        Text(
            text = item.workoutName,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
        TextWithIcon(
            label = item.exercises.toString(),
            icon = Icons.Rounded.Star,
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.BottomStart)
        )
        Icon(
            Icons.Rounded.Edit,
            contentDescription = "edit workout",
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
    }
}

@Composable
fun TextWithIcon(
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = fontSize
        )
        Icon(
            imageVector = icon,
            contentDescription = "icon_with_text"
        )
    }
}