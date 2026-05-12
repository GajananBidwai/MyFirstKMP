package org.example.project.articles.application

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import org.example.project.articles.data.ArticleRepository
import org.example.project.articles.data.ArticlesRow
import kotlin.math.abs

class ArticlesUseCase(private val repo: ArticleRepository) {

    suspend fun getArticles(forceFetch: Boolean): List<Article> {
        val articleRow = repo.getArticles(forceFetch)
        return mapArticles(articleRow = articleRow)
    }

    private fun mapArticles(articleRow: List<ArticlesRow>): List<Article> = articleRow.map { raw ->
        Article(
            title = raw.title,
            desc = raw.desc ?: "Click to find out more",
            date = getDaysAgoString(raw.date),
            imageUrl = raw.imageUrl
                ?: "https://image.cnbcfm.com/api/v1/image/107326078-1698758530118-gettyimages-1765623456-wall26362_igj6ehhp.jpg"
        )
    }

    private fun getDaysAgoString(date: String): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date).toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }
}