package com.codebaron.filmworld.models.filmdetails

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

val dummyProductionCompany = listOf(
    ProductionCompany(
        id = 5,
        logo_path = "/71BqEFAF4V3qjjMPCpLuyJFB9A.png",
        name = "Columbia Pictures",
        origin_country = "US"
    ),
    ProductionCompany(
        id = 420,
        logo_path = "/hUzeosd33nzE5MCNsZxCGEKTXaQ.png",
        name = "Marvel Studios",
        origin_country = "US"
    ),
    ProductionCompany(
        id = 84041,
        logo_path = "/nw4kyc29QRpNtFbdsBHkRSFavvt.png",
        name = "Pascal Pictures",
        origin_country = "US"
    )
)