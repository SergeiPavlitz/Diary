package com.pavlitz.diary

import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pavlitz.diary.database.diary.DiaryDataBase
import com.pavlitz.diary.database.diary.MIGRATION_1_2
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    private val TEST_DB = "migration-test"

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        DiaryDataBase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migration1To2(){
        val tableName = "diary_entries_table"
        val moodColumn = "entry_mood"
        val defaultValue = "defaultMood"
        var db = helper.createDatabase(TEST_DB, 1).apply {
            var millis = System.currentTimeMillis()
            execSQL("insert into $tableName values(1, $millis, 'topicOne', 'bodyOne')")
            millis = System.currentTimeMillis()
            execSQL("insert into $tableName values(2, $millis, 'topicTwo', 'bodyTwo')")
            millis = System.currentTimeMillis()
            execSQL("insert into $tableName values(3, $millis, 'topicThree', 'bodyThree')")
            millis = System.currentTimeMillis()
            execSQL("insert into $tableName values(4, $millis, 'topicFour', 'bodyFour')")
            close()
        }

        db = helper.runMigrationsAndValidate(TEST_DB, 2, true, MIGRATION_1_2)

        val cursor = db.query("select * from $tableName")
        assert(cursor.count == 4)
        while (cursor.moveToNext()) {
            val mood = cursor.getString(cursor.getColumnIndex(moodColumn))
            assert(mood.equals(defaultValue))
        }
    }
}