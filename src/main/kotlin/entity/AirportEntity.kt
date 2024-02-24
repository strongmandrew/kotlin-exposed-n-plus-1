package com.strongmandrew.entity

import com.strongmandrew.domain.Airport
import com.strongmandrew.table.AirportTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AirportEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<AirportEntity>(AirportTable)

    val country by AirportTable.country
    val city by AirportTable.city
    val iataCode by AirportTable.iataCode

    fun toAirport() = Airport(country, city, iataCode)
}