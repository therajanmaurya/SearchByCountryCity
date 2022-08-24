package com.task.countriescities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.countriescities.api.CountriesRepository
import com.task.countriescities.data.Countries
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

    fun fetchCountriesData() {
        repository.fetchCountries().enqueue(object : Callback<Countries> {
            override fun onResponse(call: Call<Countries>, response: Response<Countries>) {
                response.body()?.let { countries.postValue(it) }
            }

            override fun onFailure(call: Call<Countries>, t: Throwable) {
                // Show Failure UI
            }
        })
    }
}