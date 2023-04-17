package com.example.demoroomdb.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDAO {

    //
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(contact: Contact):Long

    @Update
    suspend fun updateData(contact: Contact)

    @Delete
    suspend fun deleteData(contact: Contact)

    @Query("SELECT * FROM contactTable")
    fun getContact():LiveData<List<Contact>>

}