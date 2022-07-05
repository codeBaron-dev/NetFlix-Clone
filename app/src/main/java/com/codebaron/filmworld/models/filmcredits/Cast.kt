package com.codebaron.filmworld.models.filmcredits

data class Cast(
    val adult: Boolean? = null,
    val cast_id: Int? = null,
    val character: String? = null,
    val credit_id: String? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val known_for_department: String? = null,
    val name: String? = null,
    val order: Int? = null,
    val original_name: String? = null,
    val popularity: Double? = null,
    val profile_path: String? = null
)

val dummyCast = listOf(
    Cast(
        adult = false,
        cast_id = 159,
        character = "Peter Parker / Spider-Man",
        credit_id = "61b891dd1f3319006121ecd1",
        gender = 2,
        id = 37625,
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    ),
    Cast(
        adult = false,
        cast_id = 159,
        character = "Peter Parker / Spider-Man",
        credit_id = "61b891dd1f3319006121ecd1",
        gender = 2,
        id = 37625,
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    ),
    Cast(
        adult = false,
        cast_id = 159,
        character = "Peter Parker / Spider-Man",
        credit_id = "61b891dd1f3319006121ecd1",
        gender = 2,
        id = 37625,
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    ),
    Cast(
        adult = false,
        cast_id = 159,
        character = "Peter Parker / Spider-Man",
        credit_id = "61b891dd1f3319006121ecd1",
        gender = 2,
        id = 37625,
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    )
)