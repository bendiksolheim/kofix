package kofix

import kofix.model.NestedModel
import kofix.model.SimpleModel
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class NestedModelTests {

    @Test
    fun `Can generate NestedModel without overrides`() {
        assertDoesNotThrow { fixture<NestedModel>().generate(Random).first() }
    }

    @RepeatedTest(10)
    fun `Can generate NestedModel and override nested values`() {
        val model = assertDoesNotThrow {
            fixture<NestedModel>().with(
                NestedModel::simple, fixture<SimpleModel>()
                    .with(SimpleModel::age, Int.generator(0, 1))
                    .with(SimpleModel::name, String.generator(1, 1, listOf('a')))
            ).generate(Random).first()
        }

        assertEquals(0, model.simple.age)
        assertEquals("a", model.simple.name)
    }
}