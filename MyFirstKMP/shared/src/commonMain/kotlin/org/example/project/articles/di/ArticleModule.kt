package org.example.project.articles.di

import org.example.project.articles.data.ArticleDataSource
import org.example.project.articles.data.ArticleRepository
import org.example.project.articles.data.ArticleService
import org.example.project.articles.application.ArticlesUseCase
import org.example.project.articles.presentation.ArticlesViewModel
import org.koin.dsl.module

var articleModule = module {
    single<ArticleService> { ArticleService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticleDataSource> { ArticleDataSource(get()) }
    single<ArticleRepository> { ArticleRepository(get(), get()) }
}