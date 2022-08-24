package com.jonechka.tasknavigation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val taskName: String,
    val priority: String,
    val date: Long? = null,
    val coordinates: String? = null,
    @PrimaryKey val id: Int? = null
)

class InvalidTaskException(message: String) : Exception(message)