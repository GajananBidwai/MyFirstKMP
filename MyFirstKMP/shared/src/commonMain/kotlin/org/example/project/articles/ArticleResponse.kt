package org.example.project.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtcilesResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    val articles: List<ArticlesRow>

)
