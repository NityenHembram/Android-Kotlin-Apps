package com.amvlabs.androidkotlinapps.warecover.room

import android.app.Application
import android.os.Message
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Messages::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MessageDatabase:RoomDatabase() {
    abstract fun dao(): MessageDao
    companion object{
        @Volatile
        private var INSTANCE:MessageDatabase? = null
        fun getInstance(application:Application):MessageDatabase{
            synchronized(this){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(application,MessageDatabase::class.java,"messageDb").build()
                }
            }
            return INSTANCE!!
        }
    }
}