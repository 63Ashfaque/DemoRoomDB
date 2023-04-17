package com.example.demoroomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertData(contact: Contact)

    @Update
    suspend fun updateData(contact: Contact)

    @Delete
    suspend fun deleteData(contact: Contact)

    @Query("SELECT * FROM contactTable")
    fun getContact():LiveData<List<Contact>>

}