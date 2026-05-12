package org.example.project.di

import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.articles.presentation.ArticlesViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

var viewModelsModule = module {

    viewModel { ArticlesViewModel(get()) }
}