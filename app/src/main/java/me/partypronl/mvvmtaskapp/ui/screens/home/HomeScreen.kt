package me.partypronl.mvvmtaskapp.ui.screens.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.partypronl.mvvmtaskapp.viewmodel.home.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val loadingProjects by homeViewModel.loadingProjects.collectAsState()
    val projects by homeViewModel.projects.collectAsState()

    Scaffold(
        floatingActionButton = { CreateProjectFAB(navController) }
    ) { innerPadding ->
        if(!loadingProjects) {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(projects) { project ->
                    Text(project.name)
                }
            }
        } else {
            Text("Loading...")
        }
    }
}

@Composable
fun CreateProjectFAB(
    navController: NavController
) {
    ExtendedFloatingActionButton(
        onClick = {},
        icon = { Icon(Icons.Default.Add, "Create") },
        text = { Text("Create project") }
    )
}