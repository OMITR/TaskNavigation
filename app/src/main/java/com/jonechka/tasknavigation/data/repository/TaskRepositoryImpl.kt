package com.jonechka.tasknavigation.data.repository

import com.jonechka.tasknavigation.data.local_data_source.TaskDao
import com.jonechka.tasknavigation.domain.model.Task
import com.jonechka.tasknavigation.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDao
) : TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override suspend fun getTaskById(id: Int): Task? {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        return dao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        return dao.deleteTask(task)
    }

}