package me.partypronl.mvvmtaskapp.data.service

import me.partypronl.mvvmtaskapp.data.model.Task
import me.partypronl.mvvmtaskapp.data.repository.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskService @Inject constructor(val taskRepository: TaskRepository) {
    suspend fun getTasks(): List<Task> {
        return taskRepository.getTasks()
    }
}