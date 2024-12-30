package com.example.to_do.Database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val database : todolistDatabase): ViewModel() {

// all activated and deactivates tasks
    val tasks = database.todolistdao().getAllTask()
    val deactivatedTask = database.todolistdao().getDeactivatedTasks()

    // all work tasks
    val WorkTasks = database.todolistdao().getWorkTask()
    val completedWorkTask = database.todolistdao().getAllCompletedWorkTask()

    // all personal tasks
    val PersonalTasks = database.todolistdao().getAllPersonalTask()
    val completedPersonalTask = database.todolistdao().getAllCompletedPersonalTask()

    // all wishlist tasks
    val WishListTasks = database.todolistdao().getAllWishlistTask()
    val completedWishlistTask = database.todolistdao().getAllCompletedWishlistTask()

    // all birthday tasks
    val BirthdayTasks = database.todolistdao().getAllBirthdaTask()
    val completedBirthdayTask=database.todolistdao().getAllCompletedBirthdayTask()

    // all list tasks
    val listTask = database.todolistdao().getAllListTask()
    val completedListTask= database.todolistdao().getAllCompletedListTask()

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