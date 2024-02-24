package com.strongmandrew.repository

import com.strongmandrew.domain.Flight
import com.strongmandrew.entity.FlightEntity
import com.strongmandrew.transaction.loggedTransaction
import org.jetbrains.exposed.dao.with

class MediumDaoFlightRepository : FlightRepository {

    override fun getFlightsToCountryByAirplaneYoungerThan(
        destinationCountry: String,
        youngerThanYear: Int
    ): List<Flight> =
        loggedTransaction {
            FlightEntity.all().toList().with(FlightEntity::toAirport, FlightEntity::airplane).filter { flightEntity ->

                val targetCityMatches = flightEntity.toAirport.country.equals(destinationCountry, ignoreCase = true)
                val isYoungerThanYear = flightEntity.airplane.yearReleased <= youngerThanYear

                targetCityMatches && isYoungerThanYear
            }.map(FlightEntity::toFlight)
        }

    override fun toString(): String =
        "Наивный репозиторий, работающий с Exposed DAO, но группирующий запросы к связаным сущностям"
}