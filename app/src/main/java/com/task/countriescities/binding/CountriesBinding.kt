package com.task.countriescities.binding

import androidx.databinding.BindingAdapter
import com.airbnb.epoxy.EpoxyRecyclerView
import com.task.countriescities.CountriesCitiesFragment
import com.task.countriescities.country
import com.task.countriescities.data.Countries

@BindingAdapter(value = ["fragment", "countries"], requireAll = true)
fun EpoxyRecyclerView.setCountries(
    fragment: CountriesCitiesFragment?,
    countries: Countries?
) {
    withModels {
        countries?.let {
            it.data.forEach {
                country {
                    id(it.country)
                    country(it.country)
                }
            }
        }
    }
}