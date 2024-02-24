package com.strongmandrew.service

import com.strongmandrew.domain.Flight
import com.strongmandrew.repository.FlightRepository

class FlightService(
    private val flightRepository: FlightRepository
) {
    fun getCarsWithEnginesThatStartWithLetterAndBodyTypeMatches(
        destinationCountry: String,
        youngerThanYear: Int
    ): List<Flight> = flightRepository.getFlightsToCountryByAirplaneYoungerThan(destinationCountry, youngerThanYear)
}