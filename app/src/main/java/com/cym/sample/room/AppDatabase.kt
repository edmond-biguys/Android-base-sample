package com.cym.sample.room

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cym.sample.room.dao.CourseDao
import com.cym.sample.room.entity.Course

/**
 * Created by caoj on 2022/10/25.
 */
@Database(
    entities = [Course::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getCourseDao(): CourseDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }


}

private const val DATABASE_NAME = "xzl_common_db"