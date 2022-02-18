package model

data class BeerData(
    val image_url: String? = null,
    val name: String,
    val tagline: String,
    val first_brewed: String,
    val description: String
)