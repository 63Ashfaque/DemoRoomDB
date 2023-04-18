package com.example.demoroomdb.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {

    //
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(contact: Contact):Long

    @Update
    suspend fun updateData(contact: Contact):Int

    @Delete
    suspend fun deleteData(contact: Contact):Int

    @Query("SELECT * FROM contactTable")
    fun getContact():LiveData<List<Contact>>

    @Query("SELECT * FROM contactTable ORDER BY id DESC")
    fun getContactDesc():LiveData<List<Contact>>

}