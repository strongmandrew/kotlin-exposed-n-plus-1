package annotation

import com.strongmandrew.table.AirplaneTable
import com.strongmandrew.table.AirportTable
import com.strongmandrew.table.FlightTable
import com.strongmandrew.transaction.loggedTransaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId

class InMemoryDatabaseBootstrapper {

    fun bootstrap() {
        connectToDatabase()
        createMissingTables()
        createTestData()
    }

    private fun connectToDatabase() {
        Database.connect(
            url = "jdbc:h2:mem:airport;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver",
        )
    }

    private fun createMissingTables() {
        loggedTransaction {
            SchemaUtils.createMissingTablesAndColumns(AirplaneTable, AirportTable, FlightTable)
        }
    }

    private fun createTestData() = loggedTransaction {
        val airportID1 = AirportTable.insertAndGetId {
            it[iataCode] = "LED"
            it[country] = "Russia"
            it[city] = "St.Petersburg"
        }

        val planeID1 = AirplaneTable.insertAndGetId {
            it[yearReleased] = 2015
            it[firm] = "Airbus"
            it[model] = "A320"
        }

        FlightTable.insert {
            it[toAirport] = airportID1
            it[airplane] = planeID1
        }

        val airportID2 = AirportTable.insertAndGetId {
            it[iataCode] = "VKO"
            it[country] = "Russia"
            it[city] = "Moscow"
        }

        val planeID2 = AirplaneTable.insertAndGetId {
            it[yearReleased] = 2008
            it[firm] = "Boeing"
            it[model] = "747"
        }

        FlightTable.insert {
            it[toAirport] = airportID2
            it[airplane] = planeID2
        }

        val airportID3 = AirportTable.insertAndGetId {
            it[iataCode] = "DME"
            it[country] = "Russia"
            it[city] = "Moscow"
        }

        val planeID3 = AirplaneTable.insertAndGetId {
            it[yearReleased] = 2008
            it[firm] = "Sukhoi"
            it[model] = "Superjet 100"
        }

        FlightTable.insert {
            it[toAirport] = airportID3
            it[airplane] = planeID3
        }
    }
}