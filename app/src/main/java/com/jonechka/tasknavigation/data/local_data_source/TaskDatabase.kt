package com.jonechka.tasknavigation.data.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jonechka.tasknavigation.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}