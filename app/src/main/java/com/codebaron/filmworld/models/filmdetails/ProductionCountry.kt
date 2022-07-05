package com.codebaron.filmworld.models.filmdetails

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)
val dummyProductionCountry = listOf(
    ProductionCountry(
        iso_3166_1 = "US",
        name = "United States Of America"
    ),
    ProductionCountry(
        iso_3166_1 = "US",
        name = "United States Of America"
    ),
    ProductionCountry(
        iso_3166_1 = "NGN",
        name = "Nigeria"
    ),
    ProductionCountry(
        iso_3166_1 = "IN",
        name = "India"
    ),
    ProductionCountry(
        iso_3166_1 = "CN",
        name = "China"
    )
)