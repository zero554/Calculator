package com.example.calculator.Tabs

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val icon: ImageVector,
    val title: String,
    val screen: @Composable () -> Unit
)