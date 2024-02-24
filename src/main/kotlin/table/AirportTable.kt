package com.strongmandrew.table

import org.jetbrains.exposed.dao.id.IntIdTable

object AirportTable : IntIdTable("airports") {
    val iataCode = varchar("iata_code", 32)
    val country = varchar("country", 128)
    val city = varchar("city", 128)
}