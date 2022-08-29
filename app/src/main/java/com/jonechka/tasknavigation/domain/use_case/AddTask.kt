package com.jonechka.tasknavigation.domain.use_case

import com.jonechka.tasknavigation.domain.model.InvalidTaskException
import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.repository.TaskRepository

class AddTask(
    private val repository: TaskRepository
) {

    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task) {
        if (task.taskTitle.isBlank()) {
            throw InvalidTaskException("The name of the task cannot be empty.")
        }
        repository.insertTask(task)
    }
}