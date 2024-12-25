package me.partypronl.mvvmtaskapp.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.partypronl.mvvmtaskapp.data.model.Project
import me.partypronl.mvvmtaskapp.data.service.ProjectService
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val projectService: ProjectService
): ViewModel() {
    private val _projects = MutableStateFlow<List<Project>>(emptyList())
    val projects = _projects.asStateFlow()

    private val _loadingProjects = MutableStateFlow(false)
    val loadingProjects = _loadingProjects.asStateFlow()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingProjects.value = true
            _projects.value = projectService.getProjects()
            _loadingProjects.value = false
        }
    }
}