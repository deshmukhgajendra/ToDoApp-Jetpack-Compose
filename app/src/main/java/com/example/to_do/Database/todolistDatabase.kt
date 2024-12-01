package com.example.to_do.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Tasks::class], version = 7, exportSchema = true)
abstract class todolistDatabase:RoomDatabase() {

    abstract fun todolistdao() : ToDoListDAO
}
