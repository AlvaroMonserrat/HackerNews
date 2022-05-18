package com.rrat.hackernews.viewmodel

import androidx.lifecycle.*
import com.rrat.hackernews.model.data.entities.Post
import com.rrat.hackernews.model.database.HackerNewsRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class PostViewModel(private val repository: HackerNewsRepository): ViewModel() {

    fun insert(post: Post) = viewModelScope.launch {
        repository.insertPost(post)
    }

    fun update(post: Post) = viewModelScope.launch {
        repository.updatePost(post)
    }

    /*
    fun insertOrUpdate(post: Post)  = viewModelScope.launch {
        repository.insertOrUpdatePost(post)
    }
    */

    val allPosts: LiveData<List<Post>> = repository.allPost.asLiveData()
}

class PostViewModelFactory(private val repository: HackerNewsRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PostViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return PostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}