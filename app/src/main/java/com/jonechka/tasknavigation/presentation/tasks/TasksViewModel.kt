package com.jonechka.tasknavigation.presentation.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.use_case.TasksUseCases
import com.jonechka.tasknavigation.domain.util.OrderType
import com.jonechka.tasknavigation.domain.util.TaskOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val tasksUseCases: TasksUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private var recentlyDeletedTask: Task? = null

    private var getTasksJob: Job? = null

    init {
        getTasks(TaskOrder.Timestamp(OrderType.Descending))
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.Order -> {
                if (state.value.taskOrder::class == event.taskOrder::class &&
                        state.value.taskOrder.orderType == event.taskOrder.orderType
                ) {
                    return
                }
                getTasks(event.taskOrder)
            }
            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    tasksUseCases.deleteTask(event.task)
                    recentlyDeletedTask = event.task
                }
            }
            is TasksEvent.RestoreTask -> {
                viewModelScope.launch {
                    tasksUseCases.addTask(recentlyDeletedTask ?: return@launch)
                    recentlyDeletedTask = null
                }
            }
            is TasksEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getTasks(taskOrder: TaskOrder) {
        getTasksJob?.cancel()
        getTasksJob = tasksUseCases.getTasks(taskOrder)
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,
                    taskOrder = taskOrder
                )
            }
            .launchIn(viewModelScope)
    }
}