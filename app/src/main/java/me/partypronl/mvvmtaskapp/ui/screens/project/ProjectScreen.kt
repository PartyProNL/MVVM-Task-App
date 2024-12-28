package me.partypronl.mvvmtaskapp.ui.screens.project

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
            val openTasks = project.tasks.filter { !it.completed }

            if(openTasks.isNotEmpty()) {
                Text(text = "Open", style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 8.dp, start = 8.dp))
                LazyColumn {
                    items(openTasks) { task ->
                        TaskListItem(task, project)
                    }
                }
            }

            val completedTasks = project.tasks.filter { it.completed }

            if(completedTasks.isNotEmpty()) {
                Text(text = "Completed", style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 8.dp, start = 8.dp))
                LazyColumn {
                    items(completedTasks) { task ->
                        TaskListItem(task, project)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskListItem(task: Task, project: Project, projectViewModel: ProjectViewModel = hiltViewModel()) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .height(56.dp)
            .toggleable(
                value = task.completed,
                onValueChange = { projectViewModel.markTaskAsComplete(task.uuid, it) },
                role = Role.Checkbox
            )
            .padding(horizontal = 16.dp),
    ) {
        Checkbox(
            checked = task.completed,
            onCheckedChange = null
        )

        Text(
            text = task.text,
            style = MaterialTheme.typography.bodyLarge
                .copy(textDecoration = if(task.completed) TextDecoration.LineThrough else TextDecoration.None),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun CreateTaskFAB(navController: NavController, project: Project) {
    var showCreateSheet by remember { mutableStateOf(false) }

    ExtendedFloatingActionButton(
        onClick = { showCreateSheet = true },
        icon = { Icon(Icons.Default.Add, "Add") },
        text = { Text(text = "Add task") }
    )

    CreateTaskBottomSheet(project, showCreateSheet, onDismiss = { showCreateSheet = false })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskBottomSheet(project: Project, open: Boolean, onDismiss: () -> Unit) {
    val sheetState = rememberModalBottomSheetState()

    if(open) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.End
            ) {
                OutlinedTextField(
                    value = "Test",
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Task") }
                )

                Button(onClick = {

                },
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text("Create")
                }
            }
        }
    }
}