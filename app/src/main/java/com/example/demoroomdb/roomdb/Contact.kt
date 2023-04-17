package com.example.demoroomdb.roomdb

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contactTable", indices = [Index(value = ["name","phone"], unique = true)])
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name:String,
    val phone:String,
    val createdDate:Date
)

