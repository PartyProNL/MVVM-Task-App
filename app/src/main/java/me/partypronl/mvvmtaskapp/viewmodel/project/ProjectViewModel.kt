package me.partypronl.mvvmtaskapp.viewmodel.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.partypronl.mvvmtaskapp.data.model.Project
import me.partypronl.mvvmtaskapp.data.service.ProjectService
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    val projectService: ProjectService
): ViewModel() {
    private val _project = MutableStateFlow<Project?>(null)
    val project = _project.asStateFlow()

    private val _loadingProject = MutableStateFlow(true)
    val loadingProject = _loadingProject.asStateFlow()

    fun loadProject(uuid: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadingProject.value = true
            _project.value = projectService.getProject(uuid)
            _loadingProject.value = false
        }
    }
}