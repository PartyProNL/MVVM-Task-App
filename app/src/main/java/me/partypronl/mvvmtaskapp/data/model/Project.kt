package me.partypronl.mvvmtaskapp.data.model

import java.util.UUID

data class Project(
    val name: String,
    val tasks: MutableList<Task>
) {
    val uuid = UUID.randomUUID()
}