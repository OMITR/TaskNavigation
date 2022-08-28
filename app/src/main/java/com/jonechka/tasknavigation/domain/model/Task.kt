package com.jonechka.tasknavigation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jonechka.tasknavigation.ui.theme.*

@Entity
data class Task(
    val taskTitle: String,
    val timestamp: Long,
    val date: Long? = null,
    val coordinates: String? = null,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val taskColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidTaskException(message: String) : Exception(message)