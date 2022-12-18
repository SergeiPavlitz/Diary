package com.pavlitz.diary.database.diary

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table diary_entries_table add column entry_mood text not null default 'defaultMood'")
    }

}