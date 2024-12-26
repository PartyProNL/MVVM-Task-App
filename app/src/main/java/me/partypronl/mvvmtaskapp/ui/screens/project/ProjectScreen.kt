package me.partypronl.mvvmtaskapp.ui.screens.project

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import me.partypronl.mvvmtaskapp.data.model.Project
import java.util.UUID

@Composable
fun ProjectScreen(
    navController: NavController,
    projectUuid: UUID
) {
    Scaffold(

    ) { innerPadding ->
        Text(modifier = Modifier.padding(innerPadding), text = "Project $projectUuid")
    }
}

@Composable
fun CreateTaskFAB(navController: NavController, project: Project) {

}