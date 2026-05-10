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

class ArticlesViewModel(): BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(isLoading = true))
    val articleState: StateFlow<ArticlesState> get() = _articleState

    private val useCase: ArticlesUseCase

    init {
        var httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        var service = ArticleService(httpClient)

        useCase = ArticlesUseCase(service)
        getArticles()
    }


    private fun getArticles() {
        scope.launch {

            var fetchedArticle = useCase.getArticles()

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