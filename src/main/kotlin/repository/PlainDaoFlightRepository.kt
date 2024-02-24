package com.strongmandrew.repository

import com.strongmandrew.domain.Flight
import com.strongmandrew.entity.FlightEntity
import com.strongmandrew.transaction.loggedTransaction
import org.jetbrains.exposed.sql.Op

class PlainDaoFlightRepository : FlightRepository {

    override fun getFlightsToCountryByAirplaneYoungerThan(
        destinationCountry: String,
        youngerThanYear: Int
    ): List<Flight> =
        loggedTransaction {
            FlightEntity.find {
                /* AirportTable.country.lowerCase() eq destinationCountry.lowercase()
                and (AirplaneTable.yearReleased less youngerThanYear) */
                Op.nullOp()
            }

            FlightEntity.all().filter { flightEntity ->
                val targetCityMatches = flightEntity.toAirport.country.equals(destinationCountry, ignoreCase = true)
                val isYoungerThanYear = flightEntity.airplane.yearReleased <= youngerThanYear

                targetCityMatches && isYoungerThanYear

            }.map(FlightEntity::toFlight)
        }

    override fun toString(): String = "Наивный репозиторий, работающий через Exposed DAO"
}