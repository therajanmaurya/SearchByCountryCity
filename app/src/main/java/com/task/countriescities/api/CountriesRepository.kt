package com.task.countriescities.api

import javax.inject.Inject

class CountriesRepository @Inject constructor(private val service: CountriesnowService) {

    fun fetchCountries() = service.getCountriesAndCities()
}