package com.jonechka.tasknavigation.domain.use_case

import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.repository.TaskRepository

class GetTask(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(id: Int): Task? {
        return repository.getTaskById(id)
    }
}