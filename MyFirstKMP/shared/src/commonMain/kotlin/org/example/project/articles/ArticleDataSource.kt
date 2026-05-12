package org.example.project.articles

import org.example.project.db.MyFirstKMPDatabase

class ArticleDataSource(private var database: MyFirstKMPDatabase) {

    fun getAllArticles(): List<ArticlesRow> =
        database.myFirstKMPDatabaseQueries.selectAllArticles(::mapToArticleRaw).executeAsList()


    fun insertArticles(articles: List<ArticlesRow>) {
        database.myFirstKMPDatabaseQueries.transaction {
            articles.forEach { articlesRow ->
                insertArticle(articlesRow)
            }
        }
    }

    fun insertArticle(articlesRow: ArticlesRow) {
        database.myFirstKMPDatabaseQueries.insertArticle(
            articlesRow.title,
            articlesRow.desc,
            articlesRow.date,
            articlesRow.imageUrl
        )
    }

    fun clearArticles() = database.myFirstKMPDatabaseQueries.removeAllArticles()

    private fun mapToArticleRaw(
        title: String,
        desc: String?,
        date: String,
        url: String?
    ) : ArticlesRow = ArticlesRow(title = title,
        desc = desc,
        date = date,
        imageUrl = url
    )
}