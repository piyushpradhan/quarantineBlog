package com.example.quarantineblog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quarantineblog.models.BlogModel

@Database(entities = [BlogModel::class], version=1, exportSchema = false)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun blogDao(): BlogDao

    companion object {
        @Volatile
        private var INSTANCE: BlogDatabase? = null

        fun getDatabase(context: Context): BlogDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BlogDatabase::class.java,
                    "blog_table"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}