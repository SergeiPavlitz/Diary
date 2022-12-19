package com.pavlitz.diary.database.auth

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AuthDataBaseDao {
    @Insert
    suspend fun insert(entry: AuthEntity)

    @Update
    suspend fun update(entry: AuthEntity)

    @Query("select * from auth_table order by id limit 1")
    fun getAuth(): LiveData<AuthEntity>

    @Query("delete from auth_table")
    suspend fun delete()

}