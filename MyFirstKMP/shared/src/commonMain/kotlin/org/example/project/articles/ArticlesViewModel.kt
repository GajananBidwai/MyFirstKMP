package org.example.project.articles

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.example.project.BaseViewModel

class ArticlesViewModel(
    private var useCase: ArticlesUseCase
): BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(isLoading = true))
    val articleState: StateFlow<ArticlesState> get() = _articleState


    init {
        getArticles()
    }


    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {

            _articleState.emit(ArticlesState(isLoading = true, articles = _articleState.value.articles))

            var fetchedArticle = useCase.getArticles(forceFetch)

            _articleState.emit(ArticlesState(articles = fetchedArticle))

        }
    }

    fun observeArticlesState(onChange: (ArticlesState) -> Unit) {
        scope.launch {
            articleState.collect {
                onChange(it)
            }
        }
    }

}