package com.strongmandrew.entity

import com.strongmandrew.domain.Airplane
import com.strongmandrew.table.AirplaneTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class AirplaneEntity(id: EntityID<Int>): IntEntity(id) {

    companion object : IntEntityClass<AirplaneEntity>(AirplaneTable)

    val yearReleased by AirplaneTable.yearReleased
    val firm by AirplaneTable.firm
    val model by AirplaneTable.model

    fun toAirplane() = Airplane(yearReleased, firm, model)
}