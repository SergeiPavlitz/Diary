package com.pavlitz.diary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDataBaseDao {

    @Insert
    suspend fun insert(entry: DiaryEntry)

    @Update
    suspend fun update(entry: DiaryEntry)

    @Query("select * from diary_entries_table where id = :key")
    fun get(key: Long): LiveData<DiaryEntry>

    @Query("select id, creation_date_milli, entry_topic from diary_entries_table where entry_topic = :topic order by id limit 1")
    suspend fun getLastInsertedByTopic(topic: String): DiaryItemView

    @Query("delete from diary_entries_table")
    suspend fun deleteAll()

    @Query("delete from diary_entries_table where id = :key")
    suspend fun delete(key: Long)

//    @Query("select * from diary_entries_table order by id desc")
//    fun getAll(): LiveData<List<DiaryEntry>>

    @Query("select * from diary_entries_table order by id desc")
    suspend fun getAll(): List<DiaryEntry>

    @Query("select id, creation_date_milli, entry_topic from diary_entries_table order by id desc")
    fun getAllForView(): LiveData<MutableList<DiaryItemView>>
//
//    @Query("select id, creation_date_milli, entry_topic from diary_entries_table order by id desc")
//    suspend fun getAllForView(): MutableList<DiaryItemView>


}