package com.strongmandrew.repository

import com.strongmandrew.domain.Flight

interface FlightRepository {
    fun getFlightsToCountryByAirplaneYoungerThan(destinationCountry: String, youngerThanYear: Int): List<Flight>
}