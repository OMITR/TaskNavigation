package com.jonechka.tasknavigation.presentation.add_edit_task

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jonechka.tasknavigation.domain.model.InvalidTaskException
import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.use_case.TasksUseCases
import com.jonechka.tasknavigation.util.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    application: Application,
    private val tasksUseCases: TasksUseCases,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val _taskTitle = mutableStateOf(TaskTextFieldsState(
        hint = "Enter title..."
    ))
    val taskTitle: State<TaskTextFieldsState> = _taskTitle

    private val _taskColor = mutableStateOf(Task.taskColors.random().toArgb())
    val taskColor: State<Int> = _taskColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    tasksUseCases.getTask(taskId)?.also { task ->
                        currentTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.taskTitle,
                            isHintVisible = false
                        )
                        _taskColor.value = task.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangeTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            taskTitle.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.ChangeColor -> {
                _taskColor.value = event.color
            }
            is AddEditTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        tasksUseCases.addTask(
                            Task(
                                taskTitle = taskTitle.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = taskColor.value,
                                id = currentTaskId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                        NotificationService(getApplication()).showNotification()
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save task."
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveTask: UiEvent()
    }
}
