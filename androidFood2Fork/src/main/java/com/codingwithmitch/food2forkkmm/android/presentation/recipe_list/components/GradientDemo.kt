package com.codingwithmitch.food2forkkmm.android.presentation.recipe_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codingwithmitch.food2forkkmm.android.presentation.components.RECIPE_IMAGE_HEIGHT

@Composable
fun GradientDemo(){
    val colors = listOf(
        Color.LightGray.copy(alpha = .9f),
        Color.LightGray.copy(alpha = .3f),
        Color.LightGray.copy(alpha = .9f),
    )
    val brush = linearGradient(
        colors,
        start = Offset(200f, 200f),
        end = Offset(400f, 400f),
    )
    Surface(shape = MaterialTheme.shapes.small){
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush)
        )
    }
}