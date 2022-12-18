package com.pavlitz.diary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Очень важно менять версию перед изменением ентити!!!
 *
 * https://startandroid.ru/ru/courses/architecture-components/27-course/architecture-components/541-urok-13-room-testirovanie.html
 * Одно небольшое, но важное замечание про схемы в папке schemas. Они генерируются при компиляции
 * проекта, и тут надо быть внимательными, т.к. может получиться следующая ситуация:
 * есть база версии 1 и, соответственно, файл 1.json
 * решаем поменять структуру базы
 * добавляем новое поле в Entity класс, но забываем поднять версию базы
 * компилируем проект и получаем в 1.json уже новую структуру базы
 * настоящая схема версии 1 теперь утеряна
 * После этого миграционный тест не сможет создать базу первой версии, потому что 1.json
 * описывает уже вторую версию.
 * Чтобы избежать этого, сначала всегда поднимайте версию приложения в AppDatabase классе, а потом
 * уже меняйте структуру Entity классов.
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