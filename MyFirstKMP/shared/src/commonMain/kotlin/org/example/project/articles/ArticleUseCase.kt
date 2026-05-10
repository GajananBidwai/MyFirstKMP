package org.example.project.articles

class ArticlesUseCase(private val service: ArticleService) {

    suspend fun getArticles(): List<Article> {
        val articleRow = service.fetchArticles()
        return mapArticles(articleRow = articleRow)
    }

    private fun mapArticles(articleRow: List<ArticlesRow>): List<Article> = articleRow.map { raw ->
        Article(
            title = raw.title,
            desc = raw.desc ?: "Click to find out more",
            date = raw.date,
            imageUrl = raw.imageUrl
                ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpg"
        )
    }
}