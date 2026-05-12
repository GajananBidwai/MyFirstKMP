package org.example.project.articles.di

import org.example.project.articles.ArticleDataSource
import org.example.project.articles.ArticleRepository
import org.example.project.articles.ArticleService
import org.example.project.articles.ArticlesUseCase
import org.example.project.articles.ArticlesViewModel
import org.koin.dsl.module

var articleModule = module {
    single<ArticleService> { ArticleService(get()) }
    single<ArticlesUseCase> { ArticlesUseCase(get()) }
    single<ArticlesViewModel> { ArticlesViewModel(get()) }
    single<ArticleDataSource> { ArticleDataSource(get()) }
    single<ArticleRepository> { ArticleRepository(get(), get()) }
}