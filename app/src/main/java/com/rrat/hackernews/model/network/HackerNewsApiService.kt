package com.rrat.hackernews.model.network

import com.rrat.hackernews.model.network.data.HackerNews
import com.rrat.hackernews.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HackerNewsApiService {
    private val api = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(HackerNewsAPI::class.java)

    fun getNews(query: String): Single<HackerNews.Hits>{
        return api.getNews(query)
    }
}