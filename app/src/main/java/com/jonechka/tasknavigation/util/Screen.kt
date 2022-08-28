package com.jonechka.tasknavigation.util

sealed class Screen(val route: String) {
    object TasksScreen: Screen("tasks_screen")
    object AddEditTaskScreen: Screen("add_edit_task_screen")
}