package com.strongmandrew.repository

import com.strongmandrew.domain.Flight
import com.strongmandrew.entity.AirplaneEntity
import com.strongmandrew.entity.AirportEntity
import com.strongmandrew.entity.FlightEntity
import com.strongmandrew.table.AirplaneTable
import com.strongmandrew.table.AirportTable
import com.strongmandrew.transaction.loggedTransaction
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.lowerCase

class ImprovedDaoFlightRepository : FlightRepository {

    override fun getFlightsToCountryByAirplaneYoungerThan(
        destinationCountry: String,
        youngerThanYear: Int
    ): List<Flight> = loggedTransaction {
        FlightEntity.eagerFind(AirportEntity, AirplaneEntity) {
            AirportTable.country.lowerCase() eq destinationCountry.lowercase() and (AirplaneTable.yearReleased lessEq youngerThanYear)
        }.map(FlightEntity::toFlight)
    }

    override fun toString(): String =
        "Репозиторий с переопределёнными методами поиска связанных сущностей в Entity-классе"
}