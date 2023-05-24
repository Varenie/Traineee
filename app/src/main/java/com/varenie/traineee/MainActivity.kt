package com.varenie.traineee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.varenie.traineee.ui.ExerciseParametersScreen.ExerciseParametersScreen
import com.varenie.traineee.ui.WorkoutScreen.WorkoutScreen
import com.varenie.traineee.ui.homeScreen.HomeScreen
import com.varenie.traineee.ui.homeScreen.WorkoutContent
import com.varenie.traineee.ui.homeScreen.WorkoutItem
import com.varenie.traineee.ui.theme.TraineeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TraineeeTheme {
                // A surface container using the 'background' color from the theme
//                HomeScreen()
//                WorkoutScreen(/*item = WorkoutContent("hands", 8)*/)
                ExerciseParametersScreen()
            }
        }
    }
}