package com.pavlitz.diary.database

import androidx.room.ColumnInfo

data class DiaryItemView(
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "creation_date_milli")
    val date: Long,
    @ColumnInfo(name = "entry_topic")
    val topic: String,
    @ColumnInfo(name = "entry_mood")
    val mood: String
    )