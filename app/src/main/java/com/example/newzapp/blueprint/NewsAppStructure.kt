package com.example.newzapp.blueprint

data class NewsAppStructure(
    val articles: List<Article>,
    val status: String,
    val totalResults: String
)