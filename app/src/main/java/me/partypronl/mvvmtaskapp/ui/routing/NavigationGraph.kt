package me.partypronl.mvvmtaskapp.ui.routing

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.partypronl.mvvmtaskapp.ui.screens.home.HomeScreen
import me.partypronl.mvvmtaskapp.ui.screens.project.ProjectScreen
import java.util.UUID

@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Project.route, listOf(
            navArgument("uuid") { type = NavType.StringType })
        ) {
            ProjectScreen(navController, UUID.fromString(it.arguments!!.getString("uuid")!!))
        }
    }
}