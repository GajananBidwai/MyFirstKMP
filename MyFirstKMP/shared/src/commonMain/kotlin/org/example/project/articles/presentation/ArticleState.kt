package org.example.project.articles.presentation

import org.example.project.articles.application.Article

class ArticlesState (
    val articles: List<Article> = listOf(),
    val isLoading: Boolean =  false,
    val error: String? = null
)