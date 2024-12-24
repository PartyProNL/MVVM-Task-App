package me.partypronl.mvvmtaskapp.data.service

import me.partypronl.mvvmtaskapp.data.model.Project
import me.partypronl.mvvmtaskapp.data.repository.ProjectRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectService @Inject constructor(val projectRepository: ProjectRepository) {
    fun getProjects(): List<Project> {
        return projectRepository.getProjects()
    }
}