package com.example.demoroomdb.roomdb

import android.content.Context
import androidx.room.*

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Converters::class)
abstract class ContactDataBase : RoomDatabase() {

    abstract fun contactDao(): ContactDAO

    companion object {

        @Volatile
        private var INSTANCE: ContactDataBase? = null
        fun getDataBase(context: Context): ContactDataBase {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDataBase::class.java,
                        "contactDataBase"
                    ).build()
                }

            }
            return INSTANCE!!
        }
    }
}