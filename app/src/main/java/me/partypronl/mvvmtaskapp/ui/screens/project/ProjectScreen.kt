package me.partypronl.mvvmtaskapp.ui.screens.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import me.partypronl.mvvmtaskapp.R
import me.partypronl.mvvmtaskapp.data.model.Project
import me.partypronl.mvvmtaskapp.data.model.Task
import me.partypronl.mvvmtaskapp.ui.screens.home.ProjectCard
import me.partypronl.mvvmtaskapp.viewmodel.project.ProjectViewModel
import java.util.UUID

@Composable
fun ProjectScreen(
    navController: NavController,
    projectUuid: UUID,
    projectViewModel: ProjectViewModel = hiltViewModel()
) {
    val loadingProject by projectViewModel.loadingProject.collectAsState()
    val project by projectViewModel.project.collectAsState()

    LaunchedEffect(Unit) {
        projectViewModel.loadProject(projectUuid)
    }

    Scaffold(
        floatingActionButton = { if(!loadingProject && project != null) CreateTaskFAB(navController, project!!) },
        topBar = { ProjectScreenTopBar(project, navController) }
    ) { innerPadding ->
        if(loadingProject) {
            LoadingProject(Modifier.padding(innerPadding))
        } else {
            if(project == null) {

            } else {
                ProjectScreenContent(Modifier.padding(innerPadding), navController, project!!)
            }
        }
    }
}

@Composable
fun NoTasks(modifier: Modifier = Modifier, navController: NavController) {
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
        Text("No tasks found", style = MaterialTheme.typography.titleLarge)
        Text("Add your first with the button below")

        Spacer(Modifier.height(12.dp))
        AddTaskButton(navController = navController)
    }
}

@Composable
fun AddTaskButton(
    navController: NavController
) {
    Button(
        onClick = {},
    ) {
        Text("Add task")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectScreenTopBar(project: Project?, navController: NavController) {
    TopAppBar(
        title = { Text(if(project == null) "Loading..." else project.name) },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("home")
            }) { Icon(Icons.AutoMirrored.Default.ArrowBack, "Back") }
        },
        actions = {
            IconButton(onClick = {

        }) { Icon(Icons.Default.MoreVert, "Options") }
        }
    )
}

@Composable
fun LoadingProject(modifier: Modifier) {
    LinearProgressIndicator(modifier.fillMaxWidth())
}

@Composable
fun ProjectScreenContent(modifier: Modifier, navController: NavController, project: Project) {
    Column(modifier.padding(horizontal = 8.dp)) {
        ProjectCard(
            project = project,
            navController = navController,
            clickable = false
        )

        if(project.tasks.isEmpty()) {
            NoTasks(Modifier, navController)
        } else {
        }
    }
}

@Composable
fun CreateTaskFAB(navController: NavController, project: Project) {
    ExtendedFloatingActionButton(
        onClick = {},
        icon = { Icon(Icons.Default.Add, "Add") },
        text = { Text(text = "Add task") }
    )
}