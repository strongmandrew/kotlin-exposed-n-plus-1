package com.strongmandrew.entity

import com.strongmandrew.domain.Flight
import com.strongmandrew.table.AirplaneTable
import com.strongmandrew.table.AirportTable
import com.strongmandrew.table.FlightTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.ColumnSet

class FlightEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : EagerSearchEntityClass<Int, FlightEntity>(FlightTable) {

        override val dependsOnTables: ColumnSet =
            FlightTable.innerJoin(AirplaneTable).innerJoin(AirportTable)
    }

    val airplane by AirplaneEntity referencedOn FlightTable.airplane
    val toAirport by AirportEntity referencedOn FlightTable.toAirport

    fun toFlight(): Flight = Flight(
        airplane = airplane.toAirplane(),
        toAirport = toAirport.toAirport()
    )
}