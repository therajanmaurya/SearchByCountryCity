package com.task.countriescities.binding

import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.task.countriescities.CountriesCitiesFragment
import com.task.countriescities.city
import com.task.countriescities.country
import com.task.countriescities.data.Countries
import com.task.countriescities.data.EpoxyExpandModel
import com.task.countriescities.databinding.ItemCountryBinding
import com.task.countriescities.divider

@BindingAdapter(
    value = ["fragment", "countries", "countryCityStates", "searchQuery"],
    requireAll = true
)
fun EpoxyRecyclerView.setCountries(
    fragment: CountriesCitiesFragment?,
    countries: Countries?,
    countryCityStates: List<EpoxyExpandModel>?,
    searchQuery: String?
) {
    withModels {
        countries?.let {
            it.data
                ?.map {
                    it.copy(cities = it.cities.filter {
                        it.lowercase().contains(searchQuery!!.lowercase())
                    })
                }
                ?.filter { it.country.lowercase().contains(searchQuery!!.lowercase()) || it.cities.isNotEmpty() }
                ?.forEach { country ->
                    country {
                        id(country.country)
                        country(country.country)
                        onBind { _, view, _ ->
                            (view.dataBinding as ItemCountryBinding).apply {
                                clCountry.setOnClickListener {
                                    fragment?.viewModel?.onCountryExpand(country.country)
                                }
                            }
                        }
                    }
                    if (countryCityStates?.find { it.id == country.country }?.isOpen == true) {
                        country.cities.forEach {
                            city {
                                id(it + country.country)
                                city(it)
                            }
                            if (country.cities.last() == it) {
                                divider {
                                    id(it + "divider")
                                }
                            }
                        }
                    }
                }
        }
    }
}
