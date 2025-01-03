package me.partypronl.mvvmtaskapp.data.repository

import kotlinx.coroutines.delay
import me.partypronl.mvvmtaskapp.data.model.Project
import me.partypronl.mvvmtaskapp.data.model.Task
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor() {
    private val projects = mutableListOf<Project>(
        Project("Project 1", mutableListOf(
            Task("Task 1", false),
            Task("Task 2", false)
        )),
        Project("Project 2", mutableListOf(
            Task("Task 3", false),
            Task("Task 4", true)
        ))
    )

    suspend fun getProjects(): List<Project> {
        delay(1000L)
        return projects.toList()
    }

    suspend fun findProjectByUUID(uuid: UUID): Project? {
        delay(1000L)
        return projects.find { it.uuid == uuid }
    }
}