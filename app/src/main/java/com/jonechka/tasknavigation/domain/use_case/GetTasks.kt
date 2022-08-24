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
        taskOrder: TaskOrder = TaskOrder.Date(OrderType.Descending)
    ): Flow<List<Task>> {
        return repository.getTasks().map { notes ->
            when (taskOrder.orderType) {
                is OrderType.Ascending -> {
                    when (taskOrder) {
                        is TaskOrder.Title -> notes.sortedBy { it.taskName.lowercase() }
                        is TaskOrder.Timestamp -> notes.sortedBy { it.timestamp }
                        is TaskOrder.Date -> notes.sortedBy { it.date }
                    }
                }
                is OrderType.Descending -> {
                    when (taskOrder) {
                        is TaskOrder.Title -> notes.sortedByDescending { it.taskName.lowercase() }
                        is TaskOrder.Timestamp -> notes.sortedByDescending { it.timestamp }
                        is TaskOrder.Date -> notes.sortedByDescending { it.date }
                    }
                }
            }
        }
    }
}