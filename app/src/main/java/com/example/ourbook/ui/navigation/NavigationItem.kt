package com.example.ourbook.ui.navigation

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val screen: Screen
)
