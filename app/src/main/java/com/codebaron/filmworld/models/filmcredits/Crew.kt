package com.codebaron.filmworld.models.filmcredits

data class Crew(
    val adult: Boolean? = null,
    val credit_id: String? = null,
    val department: String? = null,
    val gender: Int? = null,
    val id: Int? = null,
    val job: String? = null,
    val known_for_department: String? = null,
    val name: String? = null,
    val original_name: String? = null,
    val popularity: Double? = null,
    val profile_path: String? = null
)

val dummyCrew = listOf(
    Crew(
        adult = false,
        credit_id = "61b891dd1f3319006121ecd1",
        department = "Costume & Make-Up",
        gender = 2,
        id = 37625,
        job = "Costume Design",
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    ),
    Crew(
        adult = false,
        credit_id = "61b891dd1f3319006121ecd1",
        department = "Costume & Make-Up",
        gender = 2,
        id = 37625,
        job = "Costume Design",
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    ),
    Crew(
        adult = false,
        credit_id = "61b891dd1f3319006121ecd1",
        department = "Costume & Make-Up",
        gender = 2,
        id = 37625,
        job = "Costume Design",
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    ),
    Crew(
        adult = false,
        credit_id = "61b891dd1f3319006121ecd1",
        department = "Costume & Make-Up",
        gender = 2,
        id = 37625,
        job = "Costume Design",
        known_for_department = "Acting",
        name = "Andrew Garfield",
        original_name = "Andrew Garfield",
        popularity = 48.533,
        profile_path = "/beO5YvbTjrr5yy8hW26KVDMSr35.jpg"
    )
)