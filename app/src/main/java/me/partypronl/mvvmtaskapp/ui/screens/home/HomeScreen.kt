package me.partypronl.mvvmtaskapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.partypronl.mvvmtaskapp.viewmodel.home.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.partypronl.mvvmtaskapp.data.model.Project
import me.partypronl.mvvmtaskapp.R

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val loadingProjects by homeViewModel.loadingProjects.collectAsState()
    val projects by homeViewModel.projects.collectAsState()

    Scaffold(
        floatingActionButton = { CreateProjectFAB(navController) },
        topBar = { HomeScreenTopBar() }
    ) { innerPadding ->
        if(loadingProjects) {
            LoadingProjects(Modifier.padding(innerPadding))
        } else {
            if(projects.isEmpty()) {
                NoProjects(Modifier.padding(innerPadding), navController)
            } else {
                ProjectsList(Modifier.padding(innerPadding).padding(horizontal = 16.dp), projects, navController)
            }
        }
    }
}

@Composable
fun ProjectsList(modifier: Modifier, projects: List<Project>, navController: NavController) {
    LazyColumn(modifier) {
        items(projects) { project ->
            ProjectCard(Modifier.padding(bottom = 8.dp), project, navController)
        }
    }
}

@Composable
fun LoadingProjects(modifier: Modifier) {
    LinearProgressIndicator(modifier.fillMaxWidth())
}

@Composable
fun NoProjects(modifier: Modifier, navController: NavController) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            painter = painterResource(R.drawable.baseline_search_off_24),
            contentDescription = "No results"
        )

        Spacer(Modifier.height(12.dp))
        Text("No projects found", style = MaterialTheme.typography.titleLarge)
        Text("Create your first with the button below")

        Spacer(Modifier.height(12.dp))
        CreateProjectButton(navController = navController)
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

@Composable
fun CreateProjectButton(
    navController: NavController
) {
    Button(
        onClick = {},
    ) {
        Text("Create project")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopBar() {
    TopAppBar(
        title = { Text("Tasks") },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(Icons.Default.AccountCircle, "Account")
            }
        }
    )
}