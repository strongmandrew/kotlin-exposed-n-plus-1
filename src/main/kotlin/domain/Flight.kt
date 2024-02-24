package com.strongmandrew.domain

class Flight(
    private val airplane: Airplane,
    private val toAirport: Airport,
) {
    override fun toString(): String = "Рейс на [$airplane] в [$toAirport]"
}
