package com.strongmandrew.table

import org.jetbrains.exposed.dao.id.IntIdTable

object AirplaneTable : IntIdTable("airplanes") {
    val firm = varchar("firm", 128)
    val yearReleased = integer("year_released")
    val model = varchar("model", 128)
}