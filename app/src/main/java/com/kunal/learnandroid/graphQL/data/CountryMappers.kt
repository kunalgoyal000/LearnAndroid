package com.kunal.learnandroid.graphQL.data

import com.kunal.CountriesQuery
import com.kunal.CountryQuery
import com.kunal.learnandroid.graphQL.domain.DetailedCountry
import com.kunal.learnandroid.graphQL.domain.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.map { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital"
    )
}