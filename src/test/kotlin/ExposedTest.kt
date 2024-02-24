import annotation.WithInMemoryDatabase
import annotation.WithInMemoryDatabaseAnnotationInterceptor
import com.strongmandrew.domain.Flight
import com.strongmandrew.repository.*
import com.strongmandrew.service.FlightService
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(WithInMemoryDatabaseAnnotationInterceptor::class)
@WithInMemoryDatabase
class ExposedTest {

    @ParameterizedTest
    @MethodSource("repoProvider")
    fun getFlightsByFilter(flightRepository: FlightRepository) {
        FlightService(flightRepository).getCarsWithEnginesThatStartWithLetterAndBodyTypeMatches(
            destinationCountry = "russia",
            youngerThanYear = 2011
        ).also(::logFlights)
    }

    private fun logFlights(flights: List<Flight>) {
        buildString {
            appendLine(value = "===== Найденные сущности =====")
            flights.forEach { flight -> appendLine(flight) }
        }.let { outputString -> println(outputString) }
    }

    companion object {
        @JvmStatic
        fun repoProvider(): Stream<FlightRepository> = Stream.of(
            PlainDaoFlightRepository(),
            MediumDaoFlightRepository(),
            MixedDaoDslFlightRepository(),
            ImprovedDaoFlightRepository()
        )
    }
}