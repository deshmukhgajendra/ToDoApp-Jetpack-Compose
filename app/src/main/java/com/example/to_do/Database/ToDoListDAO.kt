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

    // All tasks active and cpmpleted
    @Query("SELECT * FROM todolist WHERE status='Active'")
    fun getAllTask() : LiveData<List<Tasks>>

    //
    @Query("SELECT * FROM todolist WHERE status='deactivated'")
    fun getDeactivatedTasks(): LiveData<List<Tasks>>


    // for work page
    @Query("SELECT * FROM todolist WHERE category ='Work' AND status='Active'")
    fun getWorkTask(): LiveData<List<Tasks>>

    @Query("SELECT * FROM todolist WHERE category='Work' AND status='deactivated'")
    fun getAllCompletedWorkTask():LiveData<List<Tasks>>

    // for personal page
    @Query("SELECT * FROM todolist WHERE category ='Personal'AND status ='Active'")
    fun getAllPersonalTask():LiveData<List<Tasks>>

    @Query("SELECT * FROM todolist WHERE category='Personal' AND status='deactivated'")
    fun getAllCompletedPersonalTask():LiveData<List<Tasks>>

    // for wishlist page
    @Query("SELECT * FROM todolist WHERE category='Wishlist'AND status ='Active'")
    fun getAllWishlistTask():LiveData<List<Tasks>>

    @Query("SELECT * FROM todolist WHERE category='Wishlist' AND status='deactivated'")
    fun getAllCompletedWishlistTask():LiveData<List<Tasks>>

    // for birthday page
    @Query("SELECT * FROM todolist WHERE  category='Birthday'AND status ='Active'")
    fun getAllBirthdaTask():LiveData<List<Tasks>>

    @Query("SELECT * FROM todolist WHERE category='Birthday' AND status='deactivated'")
    fun getAllCompletedBirthdayTask():LiveData<List<Tasks>>

    // for list page
    @Query("SELECT * FROM todolist WHERE  category='List'AND status ='Active'")
    fun getAllListTask(): LiveData<List<Tasks>>

    @Query("SELECT * FROM todolist WHERE category='List' AND status='deactivated'")
    fun getAllCompletedListTask():LiveData<List<Tasks>>

    // updates the status of tast to deactivated
    @Query("UPDATE todolist SET status= 'deactivated' WHERE task = :taskName")
    suspend fun updateStatus(taskName: String)

    @Query("UPDATE todolist SET status= 'Active' WHERE task = :taskName")
    suspend fun updateStatusToActive(taskName: String)

    @Query("SELECT * FROM todolist WHERE date = :date")
    fun getTaskForDate(date:String) : LiveData<List<Tasks>>


}