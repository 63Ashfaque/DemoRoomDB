package com.example.demoroomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contactTable")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val name:String,
    val phone:String
)

