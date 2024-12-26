package me.partypronl.mvvmtaskapp.data.service

import me.partypronl.mvvmtaskapp.data.model.Project
import me.partypronl.mvvmtaskapp.data.repository.ProjectRepository
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectService @Inject constructor(val projectRepository: ProjectRepository) {
    suspend fun getProjects(): List<Project> {
        return projectRepository.getProjects()
    }

    suspend fun getProject(uuid: UUID): Project? {
        return projectRepository.findProjectByUUID(uuid)
    }
}