package org.example.project.articles.data

import org.example.project.articles.data.ArticlesRow

class ArticleRepository(
    private val dataSource: ArticleDataSource,
    private val service: ArticleService
) {
    suspend fun getArticles(forceFetch: Boolean) : List<ArticlesRow> {

        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articleDb = dataSource.getAllArticles()
        println("Got ${articleDb.size}")

        if (articleDb.isEmpty()) {
            return fetchArticles()
        }

        return articleDb
    }

    private suspend fun fetchArticles(): List<ArticlesRow> {
        val fetchArtciles = service.fetchArticles()
        dataSource.insertArticles(fetchArtciles)
        return fetchArtciles
    }
}