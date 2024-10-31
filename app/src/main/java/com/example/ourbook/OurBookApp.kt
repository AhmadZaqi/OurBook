package com.example.ourbook

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ourbook.ui.components.BottomBar
import com.example.ourbook.ui.navigation.Screen
import com.example.ourbook.ui.screen.AboutScreen
import com.example.ourbook.ui.screen.DetailScreen
import com.example.ourbook.ui.screen.SaveDataScreen
import com.example.ourbook.ui.screen.HomeScreen

private const val EXISTING_DATA_USER_ID = "existingDataUserID"

@Composable
fun OurBookApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStack?.destination?.route
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.About.route) BottomBar(
                navController = navController,
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onEditData = {
                        navController.navigate(Screen.Edit.createRoute(it))
                    },
                    navToDetailScreen = {
                        navController.navigate(Screen.Detail.createRoute(it))
                    }
                )
            }
            composable(Screen.Add.route) {
                SaveDataScreen(
                    onBack = {navController.navigateUp()},
                    existingDataUserID = -1
                )
            }
            composable(
                Screen.Edit.route,
                arguments = listOf(
                    navArgument(EXISTING_DATA_USER_ID){
                        type = NavType.IntType
                    }
                )
            ) {
                val id = it.arguments?.getInt(EXISTING_DATA_USER_ID) ?: -1
                SaveDataScreen(
                    onBack = {navController.navigateUp()},
                    existingDataUserID = id
                )
            }
            composable(Screen.About.route) {
                AboutScreen(
                    onBack = {navController.navigateUp()}
                )
            }
            composable(
                Screen.Detail.route,
                arguments = listOf(
                    navArgument("id"){
                        type = NavType.IntType
                    }
                )
            ){
                val id = it.arguments?.getInt("id")?: -1
                DetailScreen(
                    onBack = { navController.navigateUp() },
                    id = id
                )
            }
        }
    }
}