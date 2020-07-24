package com.example.quarantineblog.database

import androidx.lifecycle.LiveData
import com.example.quarantineblog.models.BlogModel

class BlogRepository(private val blogDao: BlogDao) {

    val readAllData: LiveData<List<BlogModel>> = blogDao.readAllData()

    suspend fun addBlog(blogModel: BlogModel) {
        blogDao.addBlog(blogModel)
    }

    suspend fun updateBlog(blogModel: BlogModel) {
        blogDao.updateBlog(blogModel)
    }

    suspend fun deleteSingleBlog(blogModel: BlogModel) {
        blogDao.deleteSingleBlog(blogModel)
    }


}