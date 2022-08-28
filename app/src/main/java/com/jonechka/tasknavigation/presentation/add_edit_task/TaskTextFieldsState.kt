package com.jonechka.tasknavigation.presentation.add_edit_task

data class TaskTextFieldsState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)