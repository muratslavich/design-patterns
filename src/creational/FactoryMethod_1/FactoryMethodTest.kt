package creational.FactoryMethod_1

import org.junit.jupiter.api.*

internal class FactoryMethodTest {
    @Test
    fun FactoryMethodTest() {
        val araya = SomeGirl.newGirl("araya")

        print(araya)

        assert(17 == araya.age)
    }
}