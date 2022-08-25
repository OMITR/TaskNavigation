package com.jonechka.tasknavigation.presentation

import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.util.OrderType
import com.jonechka.tasknavigation.domain.util.TaskOrder

data class TasksState(
    val tasks: List<Task> = emptyList(),
    val taskOrder: TaskOrder = TaskOrder.Timestamp(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)