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
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val tasks by homeViewModel.tasks.collectAsState()

    Scaffold(
        floatingActionButton = { CreateTaskFAB(navController) }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(tasks) { task ->
                Text(task.text)
            }
        }
    }
}

@Composable
fun CreateTaskFAB(
    navController: NavController
) {
    ExtendedFloatingActionButton(
        onClick = {},
        icon = { Icon(Icons.Default.Add, "Add") },
        text = { Text("Add task") }
    )
}