package com.strongmandrew.domain

class Airport(
    private val country: String,
    private val city: String,
    private val iataCode: String,
) {
    override fun toString(): String = "Аэропорт в н.п. $city, $country ($iataCode)"
}
