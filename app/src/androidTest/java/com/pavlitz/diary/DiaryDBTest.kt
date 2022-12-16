package com.pavlitz.diary

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pavlitz.diary.database.DiaryDataBase
import com.pavlitz.diary.database.DiaryDataBaseDao
import com.pavlitz.diary.database.DiaryEntry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DiaryDBTest {

    private lateinit var dao: DiaryDataBaseDao
    private lateinit var db: DiaryDataBase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.databaseBuilder(context, DiaryDataBase::class.java, "test_db_name").build()
//        db = DiaryDataBase.getInstance(context)
        dao = db.diaryDataBaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.clearAllTables()
        db.close()
    }

//    @Test
//    fun getAllTest() {
//        val all = dao.getAll()
//        assert(all.isNotEmpty())
//        println(all)
//    }
//
//    @Test
//    fun getAllForViewTest(){
//        val all = dao.getAllForView()
//        assert(all.isNotEmpty())
//        println(all)
//    }

    @Test
    @Throws(Exception::class)
    suspend fun writeEntryAndRead() {
        val e = DiaryEntry(7)
        dao.insert(e)
        val getted = dao.get(7)
        assert(getted != null)
        assert(e.id == getted?.id)
    }

    @Test
    suspend fun updateEntry() {
        val e = DiaryEntry(8, 0, "eight", "eightBody")
        dao.insert(e)
        val getted = dao.get(8)
        assert(getted != null)
        if (getted != null) {
            getted.topic = "changed"
            dao.update(getted)
        }
        val gettedAgain = dao.get(8)
        assert(gettedAgain != null)
        if (gettedAgain != null) {
            assert(gettedAgain.topic.equals("changed"))
        }
    }

    @Test
    suspend fun clearAll() {
        val e = DiaryEntry(7)
        dao.insert(e)
        dao.deleteAll()
        val getted = dao.get(7)
        assert(getted == null)
    }
}