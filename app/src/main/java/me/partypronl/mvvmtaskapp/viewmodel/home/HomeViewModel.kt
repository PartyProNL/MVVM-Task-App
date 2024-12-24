package me.partypronl.mvvmtaskapp.viewmodel.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.partypronl.mvvmtaskapp.data.model.Task
import me.partypronl.mvvmtaskapp.data.service.TaskService
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val taskService: TaskService
): ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks = _tasks.asStateFlow()

    init {
        loadTasks()
    }

    private fun loadTasks() {
        _tasks.value = taskService.getTasks()
    }
}