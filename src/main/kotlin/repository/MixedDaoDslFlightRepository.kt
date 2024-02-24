package com.strongmandrew.repository

import com.strongmandrew.domain.Flight
import com.strongmandrew.entity.AirplaneEntity
import com.strongmandrew.entity.AirportEntity
import com.strongmandrew.entity.FlightEntity
import com.strongmandrew.table.AirplaneTable
import com.strongmandrew.table.AirportTable
import com.strongmandrew.table.FlightTable
import com.strongmandrew.transaction.loggedTransaction
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.selectAll

class MixedDaoDslFlightRepository : FlightRepository {

    override fun getFlightsToCountryByAirplaneYoungerThan(
        destinationCountry: String,
        youngerThanYear: Int
    ): List<Flight> =
        loggedTransaction {
            FlightTable.innerJoin(AirportTable).innerJoin(AirplaneTable).selectAll().where {
                AirportTable.country.lowerCase() eq destinationCountry.lowercase()
            }.andWhere {
                AirplaneTable.yearReleased lessEq youngerThanYear
            }.map { row ->
                AirplaneEntity.wrapRow(row)
                AirportEntity.wrapRow(row)
                FlightEntity.wrapRow(row).toFlight()
            }
        }

    override fun toString(): String =
        "Репозиторий, комбинирующий работу с Exposed DAO и DSL, а также кэширующий связанные сущности"
}