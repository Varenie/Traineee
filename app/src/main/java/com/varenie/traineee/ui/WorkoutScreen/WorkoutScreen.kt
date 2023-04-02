package com.varenie.traineee.ui.WorkoutScreen

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.varenie.traineee.ui.homeScreen.WorkoutContent
import com.varenie.traineee.ui.theme.BlueViolet1
import com.varenie.traineee.ui.theme.ButtonBlue
import com.varenie.traineee.ui.theme.DeepBlue

@Preview
@Composable
fun WorkoutScreen(
//    item: WorkoutContent
) {
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ) {
        ExerciseSection(list = listOf(
            ExerciseContent("Test1", "some description"),
            ExerciseContent("Test2", "some description"),
            ExerciseContent("Test3", "some description"),
            ExerciseContent("Test4", "some description"),
            ExerciseContent("Test5", "some description"),
            ExerciseContent("Test6", "some description"),
        ))
    }
}

@Composable
fun ExerciseSection(
    list: List<ExerciseContent>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(list.size) {
            ExerciseItem(item = list[it])
        }
    }
}

@Composable
fun ExerciseItem(
    item: ExerciseContent
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(6.dp)
            .aspectRatio(5f)
            .clip(RoundedCornerShape(20.dp))
            .background(ButtonBlue.copy(alpha = 0.5F))
    ) {
        Row {
            Image(
                bitmap = convertToBitmap(item.image, LocalContext.current),
                contentDescription = "Exercise image",
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(40.dp))
                    .background(BlueViolet1)
                    .size(60.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = item.name,
                    modifier = Modifier
                        .padding(start = 6.dp, top = 6.dp)
                        .align(Alignment.Start),
                    color = Color.White
                )
                Text(
                    text = item.description,
                    modifier = Modifier
                        .padding(start = 6.dp, top = 6.dp)
                        .align(Alignment.Start),
                    color = Color.White
                )
            }
        }
    }
}

private fun convertToBitmap(image: ByteArray?, context: Context): ImageBitmap {
    image?.let {
        return BitmapFactory.decodeByteArray(it, 0, it.size).asImageBitmap()
    }
    return BitmapFactory.decodeResource(context.resources, com.varenie.traineee.R.drawable.workout).asImageBitmap()
}

