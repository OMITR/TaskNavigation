package com.jonechka.tasknavigation.domain.use_case

import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.repository.TaskRepository
import com.jonechka.tasknavigation.domain.util.OrderType
import com.jonechka.tasknavigation.domain.util.TaskOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasks(
    private val repository: TaskRepository
) {

    operator fun invoke(
        taskOrder: TaskOrder = TaskOrder.Timestamp(OrderType.Descending)
    ): Flow<List<Task>> {
        return repository.getTasks().map { tasks ->
            when (taskOrder.orderType) {
                is OrderType.Ascending -> {
                    when (taskOrder) {
                        is TaskOrder.Title -> tasks.sortedBy { it.taskTitle.lowercase() }
                        is TaskOrder.Timestamp -> tasks.sortedBy { it.timestamp }
                        is TaskOrder.Color -> tasks.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when (taskOrder) {
                        is TaskOrder.Title -> tasks.sortedByDescending { it.taskTitle.lowercase() }
                        is TaskOrder.Timestamp -> tasks.sortedByDescending { it.timestamp }
                        is TaskOrder.Color -> tasks.sortedByDescending { it.color }
                    }
                }
            }
        }
    }
}