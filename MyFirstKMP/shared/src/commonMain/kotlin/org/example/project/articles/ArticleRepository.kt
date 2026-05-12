package org.example.project.articles

class ArticleRepository(
    private val dataSource: ArticleDataSource,
    private val service: ArticleService
) {
    suspend fun getArticles() : List<ArticlesRow> {
        val articleDb = dataSource.getAllArticles()
        println("Got ${articleDb.size}")

        if (articleDb.isEmpty()) {
            val fetchArtciles = service.fetchArticles()
            dataSource.insertArticles(fetchArtciles)
            return fetchArtciles
        }

        return articleDb
    }
}