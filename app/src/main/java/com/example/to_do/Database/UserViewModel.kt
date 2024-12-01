package com.example.to_do.Database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val database : todolistDatabase): ViewModel() {


    val tasks = database.todolistdao().getAllTask()
    val WorkTasks = database.todolistdao().getWorkTask()
    val PersonalTasks = database.todolistdao().getAllPersonalTask()
    val WishListTasks = database.todolistdao().getAllWishlistTask()
    val BirthdayTasks = database.todolistdao().getAllBirthdaTask()
    val listTask = database.todolistdao().getAllListTask()
    val deactivatedTask = database.todolistdao().getDeactivatedTasks()

    fun insertTask(tasks :Tasks){
        viewModelScope.launch{
            database.todolistdao().insertTask(tasks)
        }
    }

    fun deleteTask(tasks: Tasks){
        viewModelScope.launch{
            database.todolistdao().deleteTask(tasks)
        }
    }

    fun updateTask(taskName:String){
        viewModelScope.launch {
            database.todolistdao().updateStatus(taskName)
        }
    }
}