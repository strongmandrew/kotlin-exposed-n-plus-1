package com.strongmandrew.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object FlightTable : IntIdTable("flights") {
    val airplane = reference("airplane_id", AirplaneTable, onDelete = ReferenceOption.CASCADE)
    val toAirport = reference("to_airport_id", AirportTable, onDelete = ReferenceOption.CASCADE)
}