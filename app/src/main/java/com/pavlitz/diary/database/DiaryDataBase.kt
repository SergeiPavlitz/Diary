package com.pavlitz.diary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Очень важно
 */
@Database(entities = [DiaryEntry::class], version = 2)
abstract class DiaryDataBase : RoomDatabase() {

    abstract val diaryDataBaseDao: DiaryDataBaseDao

    companion object {

        @Volatile
        private var INSTANCE: DiaryDataBase? = null

        fun getInstance(context: Context): DiaryDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context, DiaryDataBase::class.java, "diary_db")
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this lesson. You can learn more about
                        // migration with Room in this blog post:
                        // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
//                        .fallbackToDestructiveMigration()
//                        .allowMainThreadQueries()
                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}