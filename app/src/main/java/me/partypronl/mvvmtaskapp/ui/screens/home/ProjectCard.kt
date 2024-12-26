package me.partypronl.mvvmtaskapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import me.partypronl.mvvmtaskapp.data.model.Project

@Composable
fun ProjectCard(modifier: Modifier, project: Project, navController: NavController, clickable: Boolean = true) {
    val completedTasks = project.tasks.filter { it.completed }.size

    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .then(if(clickable) {
                Modifier.clickable(onClick = {
                    navController.navigate("project/${project.uuid}")
                })
            } else Modifier)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.large)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = project.name,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge
            )

            IconButton(onClick = {

            }) {
                Icon(Icons.Default.MoreVert, "Options")
            }
        }

        Text(
            text = "$completedTasks/${project.tasks.size} tasks completed",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(top = 24.dp, bottom = 4.dp)
        )

        LinearProgressIndicator(
            progress = { completedTasks / project.tasks.size.toFloat() },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(4.dp)),
            trackColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}