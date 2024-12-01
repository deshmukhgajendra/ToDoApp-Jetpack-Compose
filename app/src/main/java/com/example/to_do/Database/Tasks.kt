package com.example.to_do.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="todolist")
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val task:String,
    val date:String,
    val category: String,
    val status: String
)
