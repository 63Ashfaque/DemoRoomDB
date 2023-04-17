package com.example.demoroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var dataBase: ContactDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBase =
            Room.databaseBuilder(applicationContext, ContactDataBase::class.java, "contactDataBase")
                .build()

        GlobalScope.launch {
            dataBase.contactDao().insertData(Contact(0, "Ashu", "9876543210"))

        }


    }
}