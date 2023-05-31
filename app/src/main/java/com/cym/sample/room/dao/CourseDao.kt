package com.cym.sample.room.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cym.sample.room.entity.Course

/**
 * Created by caoj on 2022/10/25.
 */
@Dao
interface CourseDao {
    @Query("SELECT * FROM course")
    fun getAll(): List<Course>

    @Query("SELECT * FROM course")
    fun getAll2(): Cursor

    @Query("SELECT * FROM course WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Course>

    @Query("SELECT * FROM course WHERE name = :name")
    fun findByName(name: String): List<Course>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg courses: Course)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll2(courses: List<Course>)

}