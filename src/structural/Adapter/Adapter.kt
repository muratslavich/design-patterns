package structural.Adapter

import java.math.*

interface Temperature {
    var temperature: Double
}

open class CelsiusTemperature(
    override var temperature: Double
): Temperature

class FahrenheitTemperature(
    override var temperature: Double
): Temperature

class FahrenheitAdapter(
    private val celsiusTemperature: CelsiusTemperature
) {
    fun convertToFahrenheitTemperature(): FahrenheitTemperature = FahrenheitTemperature(
        ((BigDecimal.valueOf(celsiusTemperature.temperature)
            .setScale(2) * BigDecimal(9) / BigDecimal(5)) + BigDecimal(32))
            .toDouble()
    )
}

fun main() {
    val temperature = CelsiusTemperature(45.0)

    val fahrenheitTemperature = FahrenheitAdapter(temperature).convertToFahrenheitTemperature()

    print(fahrenheitTemperature.temperature)
}