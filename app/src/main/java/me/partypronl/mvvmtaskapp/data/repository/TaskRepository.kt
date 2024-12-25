package me.partypronl.mvvmtaskapp.data.repository

import kotlinx.coroutines.delay
import me.partypronl.mvvmtaskapp.data.model.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor() {
    private val tasks = mutableListOf<Task>(
        Task("Task 1", false),
        Task("Task 2", false),
    )

    suspend fun getTasks(): List<Task> {
        delay(1000L)
        return tasks.toList()
    }
}