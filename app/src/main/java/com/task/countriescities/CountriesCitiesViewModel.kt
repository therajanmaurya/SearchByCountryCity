package com.task.countriescities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.countriescities.api.CountriesRepository
import com.task.countriescities.data.Countries
import com.task.countriescities.data.EpoxyExpandModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback;
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CountriesCitiesViewModel @Inject constructor(
    private val repository: CountriesRepository
) : ViewModel() {

    var countries = MutableLiveData<Countries>()
    val countryCityStates = MutableLiveData<List<EpoxyExpandModel>>(arrayListOf())

    fun fetchCountriesData() {
        repository.fetchCountries().enqueue(object : Callback<Countries> {
            override fun onResponse(call: Call<Countries>, response: Response<Countries>) {
                val oldCountryCityStates = countryCityStates.value
                response.body()?.let {
                    countries.postValue(it)
                    val countryStates = mutableListOf<EpoxyExpandModel>()
                    it.data.forEach { country ->
                        oldCountryCityStates?.find { it.id == country.country }?.let {
                            countryStates.add(it)
                        } ?: run {
                            countryStates.add(EpoxyExpandModel(country.country))
                        }
                    }
                    countryCityStates.postValue(countryStates)
                }
            }

            override fun onFailure(call: Call<Countries>, t: Throwable) {
                // Show Failure UI
            }
        })
    }

    fun onCountryExpand(id: String) {
        val oldValue = countryCityStates.value
        val newValue = oldValue?.map {
            it.copy(isOpen = if (it.id == id) !it.isOpen else false)
        }
        countryCityStates.postValue(newValue?.toMutableList())
    }
}