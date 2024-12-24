package me.partypronl.mvvmtaskapp.ui.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.partypronl.mvvmtaskapp.viewmodel.home.HomeViewModel
import androidx.compose.runtime.getValue

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val tasks by homeViewModel.tasks.collectAsState()

    LazyColumn {
        items(tasks) { task ->
            Text(task.text)
        }
    }
}