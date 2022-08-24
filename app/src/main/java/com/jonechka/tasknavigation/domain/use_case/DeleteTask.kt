package com.jonechka.tasknavigation.domain.use_case

import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.repository.TaskRepository

class DeleteTask(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(task: Task) {
        repository.deleteTask(task)
    }
}