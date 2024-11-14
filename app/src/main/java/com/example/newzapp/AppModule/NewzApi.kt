package com.example.newzapp.AppModule

import com.example.newzapp.blueprint.NewsAppStructure
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface NewzApi {
    @GET(value = "v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "6ffdc7c356274bca90a8e90e2a50984e"
    ):NewsAppStructure
}