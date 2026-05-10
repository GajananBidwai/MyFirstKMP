package org.example.project.articles

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.BaseViewModel

class ArticlesViewModel(): BaseViewModel() {

    private val _articleState: MutableStateFlow<ArticlesState> = MutableStateFlow(ArticlesState(isLoading = true))
    val articleState: StateFlow<ArticlesState> get() = _articleState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(1500)

            var fetchedArticle = fetchArticles()

            _articleState.emit(ArticlesState(error = "Something went wrong"))

            delay(1500)

            _articleState.emit(ArticlesState(articles = fetchedArticle))

        }
    }

    suspend fun fetchArticles(): List<Article> = mockArticles

    private val mockArticles = listOf(
        Article(
            title = "Stock market today: Live updates - CNBC",
            desc = "Futures were higher in premarket trading as Wall Street tried to regain its footing.",
            date = "2023-11-09",
            imageUrl = "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpg"
        ),
        Article(
            title = "Best iPhone Deals (2023): Carrier Deals, Unlocked iPhones",
            desc = "Apple's smartphones rarely go on sale, but if you're looking to upgrade (or you're gift shopping), here are the best deals available.",
            date = "2023-11-09",
            imageUrl = "https://media.wired.com/photos/622aa5c8cca6acf55fb70b57/191:100/w_1280,c_limit/iPhone-13-Pro-Colors-SOURCE"
        ),
        Article(
            title = "Samsung details 'Galaxy AI' and a feature that can translate phone calls in real time",
            desc = "In a new blog post, Samsung previewed what it calls a new era of Galaxy AI coming to its smartphones and devices.",
            date = "2023-11-09",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/3/3f/JPEG_example_flower.jpg?utm_source=commons.wikimedia.org&utm_campaign=index&utm_content=original"
        )
    )

}