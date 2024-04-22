package com.kunal.learnandroid.graphQL.data

import com.apollographql.apollo3.ApolloClient
import com.kunal.CountriesQuery
import com.kunal.CountryQuery
import com.kunal.learnandroid.graphQL.domain.CountryClient
import com.kunal.learnandroid.graphQL.domain.DetailedCountry
import com.kunal.learnandroid.graphQL.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}