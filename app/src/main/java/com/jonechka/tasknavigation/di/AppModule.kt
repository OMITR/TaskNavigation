package com.jonechka.tasknavigation.di

import android.app.Application
import androidx.room.Room
import com.jonechka.tasknavigation.data.local_data_source.TaskDatabase
import com.jonechka.tasknavigation.data.repository.TaskRepositoryImpl
import com.jonechka.tasknavigation.domain.repository.TaskRepository
import com.jonechka.tasknavigation.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TasksUseCases {
        return TasksUseCases(
            getTasks = GetTasks(repository),
            getTask = GetTask(repository),
            addTask = AddTask(repository),
            deleteTask = DeleteTask(repository)
        )
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }
}