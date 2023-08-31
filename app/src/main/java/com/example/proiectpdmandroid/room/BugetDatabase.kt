package com.example.proiectpdmandroid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BugetDataClass::class], version = 1, exportSchema = false)
abstract class BugetDatabase : RoomDatabase() {

    abstract fun bugetDao() : BugetDao

    companion object{
        @Volatile
        private  var INSTANCE: BugetDatabase? = null

        fun getDatabase(context: Context): BugetDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BugetDatabase::class.java,
                    "buget_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}