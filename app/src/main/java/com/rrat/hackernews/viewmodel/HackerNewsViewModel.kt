package com.rrat.hackernews.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rrat.hackernews.model.network.data.HackerNews
import com.rrat.hackernews.model.network.HackerNewsApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class HackerNewsViewModel : ViewModel(){
    private val hackerNewsApiService = HackerNewsApiService()
    private val compositeDisposable = CompositeDisposable()

    val isLoading =MutableLiveData<Boolean>()
    val hackerNewsResponse = MutableLiveData<HackerNews.Hits>()

    fun getHackerNewsFromAPI(query: String)
    {
        isLoading.value = true
        compositeDisposable.add(hackerNewsApiService.getNews(query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<HackerNews.Hits>()
                {
                    override fun onSuccess(value: HackerNews.Hits) {
                        isLoading.value = false
                        hackerNewsResponse.value = value
                    }

                    override fun onError(e: Throwable) {
                        isLoading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}