package com.example.newzapp.repository

import com.example.newzapp.AppModule.NewzApi
import com.example.newzapp.blueprint.NewsAppStructure
import com.example.newzapp.dataOrException.DataOrException
import javax.inject.Inject

class AppRepository @Inject constructor(private val api: NewzApi) {
    suspend fun getAllNewz(): DataOrException<NewsAppStructure, Boolean, Exception> {
        val data =
            DataOrException<NewsAppStructure, Boolean, Exception>()
        try {
            data.loading = true
            data.data = api.getNews()
            if (data.data.toString().isNotEmpty()) data.loading = false

        } catch (exception: Exception) {
            data.e = exception
            data.loading = false
        }
        return data
    }

}