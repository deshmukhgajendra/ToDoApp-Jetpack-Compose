package com.example.to_do.Database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): todolistDatabase{
        return Room.databaseBuilder( context,
            todolistDatabase::class.java,
            "TaskDatabase"
        ).fallbackToDestructiveMigration().build()
    }

    fun provideDao(database: todolistDatabase):ToDoListDAO{

        return database.todolistdao()
    }
}