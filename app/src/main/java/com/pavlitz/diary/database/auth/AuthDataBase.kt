package com.pavlitz.diary.database.auth

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AuthEntity::class], version = 1, exportSchema = false)
abstract class AuthDataBase : RoomDatabase() {

    abstract val authDataBaseDao: AuthDataBaseDao

    companion object {

        @Volatile
        private var INSTANCE: AuthDataBase? = null

        fun getInstance(context: Context): AuthDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AuthDataBase::class.java, "auth_db")
                        .fallbackToDestructiveMigration()
//                        .allowMainThreadQueries()
//                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}