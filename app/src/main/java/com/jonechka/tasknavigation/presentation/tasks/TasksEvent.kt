package com.jonechka.tasknavigation.presentation.tasks

import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.util.TaskOrder

sealed class TasksEvent {
    data class Order(val taskOrder: TaskOrder): TasksEvent()
    data class DeleteTask(val task: Task): TasksEvent()
    object RestoreTask: TasksEvent()
    object ToggleOrderSection: TasksEvent()
}