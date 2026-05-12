package org.example.project.articles.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.articles.data.ArticlesRow

@Serializable
data class ArtcilesResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    val articles: List<ArticlesRow>

)
