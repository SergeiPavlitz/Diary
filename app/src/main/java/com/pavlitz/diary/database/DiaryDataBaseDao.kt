package com.pavlitz.diary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDataBaseDao {

    @Insert
    fun insert(entry: DiaryEntry)

    @Update
    fun update(entry: DiaryEntry)

    @Query("select * from diary_entries_table where id = :key")
    fun get(key: Long): DiaryEntry?

    @Query("delete from diary_entries_table")
    fun clear()

    @Query("select * from diary_entries_table order by id desc")
    fun getAll(): LiveData<List<DiaryEntry>>



}