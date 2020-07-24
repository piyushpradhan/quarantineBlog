package com.example.quarantineblog.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quarantineblog.models.BlogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BlogViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<BlogModel>>
    private val repository: BlogRepository

    init {
        val blogDao = BlogDatabase.getDatabase(application).blogDao()
        repository = BlogRepository(blogDao)
        readAllData = repository.readAllData
    }

    fun addBlog(blogModel: BlogModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBlog(blogModel)
        }
    }

    fun updateBlog(blogModel: BlogModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBlog(blogModel)
        }
    }

    fun deleteSingleBlog(blogModel: BlogModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSingleBlog(blogModel)
        }
    }

}