package com.example.to_do.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoListDAO  {

    @Insert
    suspend fun insertTask(tasks:Tasks)

    @Delete
    suspend fun deleteTask(tasks:Tasks)

    @Query("SELECT * FROM todolist")
    fun getAllTask() : LiveData<List<Tasks>>


    // and AND operator to add another condition to the where clause

    // for work page
    @Query("SELECT * FROM todolist WHERE category ='Work' AND status='Active'")
    fun getWorkTask(): LiveData<List<Tasks>>

    // for personal page
    @Query("SELECT * FROM todolist WHERE category ='Personal'AND status ='Active'")
    fun getAllPersonalTask():LiveData<List<Tasks>>

    // for wishlist page
    @Query("SELECT * FROM todolist WHERE category='Wishlist'AND status ='Active'")
    fun getAllWishlistTask():LiveData<List<Tasks>>

    // for birthday page
    @Query("SELECT * FROM todolist WHERE  category='Birthday'AND status ='Active'")
    fun getAllBirthdaTask():LiveData<List<Tasks>>

    // for list page
    @Query("SELECT * FROM todolist WHERE  category='List'AND status ='Active'")
    fun getAllListTask(): LiveData<List<Tasks>>

    //
    @Query("SELECT * FROM todolist WHERE status='Deactivated'AND status ='Active'")
    fun getDeactivatedTasks(): LiveData<List<Tasks>>

    @Query("UPDATE todolist SET status= 'deactivated' WHERE task = :taskName")
    suspend fun updateStatus(taskName: String)



}