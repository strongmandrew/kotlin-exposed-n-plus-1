package com.strongmandrew.domain

class Airplane(
    private val yearReleased: Int,
    private val firm: String,
    private val model: String,
) {
    override fun toString(): String = "Самолёт $firm $model $yearReleased года выпуска"
}