package com.example.ourbook.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ourbook.ui.navigation.NavigationItem
import com.example.ourbook.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier,
    ) {
        val navBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStack?.destination?.route
        val navItem = listOf(
            NavigationItem(
                title = "Home",
                selectedIcon = Icons.Default.Home,
                unselectedIcon = Icons.Outlined.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Add",
                selectedIcon = Icons.Default.Add,
                unselectedIcon = Icons.Outlined.Add,
                screen = Screen.Add
            ),
            NavigationItem(
                title = "About",
                selectedIcon = Icons.Default.Info,
                unselectedIcon = Icons.Outlined.Info,
                screen = Screen.About
            )
        )

        navItem.map { menu ->
            NavigationBarItem(
                selected = currentRoute == menu.screen.route,
                onClick = {
                    navController.navigate(menu.screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (currentRoute == menu.screen.route) menu.selectedIcon else menu.unselectedIcon,
                        contentDescription = menu.title
                    )
                },
                label = {
                    Text(text = menu.title)
                }
            )
        }
    }
}