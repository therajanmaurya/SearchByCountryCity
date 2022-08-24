package com.task.countriescities.data

data class Countries(
    val error: Boolean,
    val msg: String,
    val data: List<Country> = emptyList()
)

data class Country(
    val iso2: String,
    val iso3: String,
    val country: String,
    val cities: List<String> = emptyList()
)