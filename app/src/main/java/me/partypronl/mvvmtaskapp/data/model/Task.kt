package me.partypronl.mvvmtaskapp.data.model

import java.util.Calendar
import java.util.UUID

data class Task(
    var text: String,
    var completed: Boolean
) {
    val uuid = UUID.randomUUID()
    val createdAt = Calendar.getInstance().timeInMillis
}