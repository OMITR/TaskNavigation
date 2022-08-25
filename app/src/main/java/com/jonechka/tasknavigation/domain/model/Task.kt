package com.jonechka.tasknavigation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.Job

@Entity
data class Task(
    val taskName: String,
    val timestamp: Long,
    val date: Long? = null,
    val coordinates: String? = null,
    @PrimaryKey val id: Int? = null
)

class InvalidTaskException(message: String) : Exception(message)