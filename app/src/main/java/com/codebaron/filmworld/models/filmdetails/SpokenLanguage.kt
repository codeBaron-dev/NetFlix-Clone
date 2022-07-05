package com.codebaron.filmworld.models.filmdetails

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

val dummySpokenLanguage = listOf(
    SpokenLanguage(
        english_name = "English",
        iso_639_1 = "en",
        "English"
    ),
    SpokenLanguage(
        english_name = "French",
        iso_639_1 = "fn",
        "French"
    ),
    SpokenLanguage(
        english_name = "Igbo",
        iso_639_1 = "ib",
        "Igbo"
    ),
    SpokenLanguage(
        english_name = "Yoruba",
        iso_639_1 = "yb",
        "Yoruba"
    )
)