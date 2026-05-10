package org.example.project.articles

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticleService(private var httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"
    private val apiKey = "095bdd7b8bc241e4bc0d51c9baea071d"

    suspend fun fetchArticles(): List<ArticlesRow> {
        val response: ArtcilesResponse = httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey\n").body()

        return response.articles
    }
}