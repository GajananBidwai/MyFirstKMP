package org.example.project.di

import org.example.project.articles.di.articleModule

val sharedKoinModules = listOf(
    articleModule,
    networkModule
)