package com.example.quarantineblog.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.quarantineblog.models.BlogModel

@Dao
abstract interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBlog(blogModel: BlogModel)

    @Query("SELECT * FROM blog_table ORDER BY date DESC")
    fun readAllData(): LiveData<List<BlogModel>>

    @Delete
    suspend fun deleteSingleBlog(blogModel: BlogModel)

    @Update
    suspend fun updateBlog(blogModel: BlogModel)

}