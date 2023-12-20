package faker

import faker.model.DayEnum
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.random.Random
import kotlin.test.assertIs
import kotlin.test.assertNotNull

class DayEnumTests {
    @RepeatedTest(10)
    fun `Can generate enums`() {
        val enum = assertDoesNotThrow { fake<DayEnum>().generate(Random).first() }

        assertNotNull(enum)
        assertIs<DayEnum>(enum)
    }
}