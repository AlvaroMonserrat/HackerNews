package com.rrat.hackernews.model.network

import com.rrat.hackernews.model.network.data.HackerNews
import com.rrat.hackernews.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsAPI {
    @GET(Constants.API_ENDPOINT)
    fun getNews(@Query(Constants.QUERY) query: String): Single<HackerNews.Hits>
}