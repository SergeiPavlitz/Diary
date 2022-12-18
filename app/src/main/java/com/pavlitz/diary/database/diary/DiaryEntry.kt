package com.pavlitz.diary.database.diary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Очень важно менять версию класса БД перед изменением ентити!!!
 * Смотри описание DiaryDataBase
 */
@Entity(tableName = "diary_entries_table")
data class DiaryEntry(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "creation_date_milli")
    var creationDate: Long = 0L,

    @ColumnInfo(name = "entry_topic")
    var topic: String = "",

    @ColumnInfo(name = "entry_body")
    var body: String = "",

    @ColumnInfo(name = "entry_mood")
    var mood: String = ""

)