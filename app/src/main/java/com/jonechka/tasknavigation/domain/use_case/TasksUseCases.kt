package com.jonechka.tasknavigation.domain.use_case

data class TasksUseCases(
    val getTask: GetTask,
    val getTasks: GetTasks,
    val addTask: AddTask,
    val deleteTask: DeleteTask
)
