package creational.prototype

class Bike: Cloneable {
    var gears: Int
    var bikeType: String
    var model: String

    init {
        bikeType = "Standart"
        model = "Leopard"
        gears = 4
    }

    fun makeAdvanced(): Bike {
        bikeType = "Advanced"
        model = "Jaguar"
        gears = 9
        return this
    }

    public override fun clone() = Bike()

    override fun toString(): String {
        return "Bike(gears=$gears, bikeType=$bikeType, model=$model)"
    }


}

fun main() {
    val bike = Bike()
    val basicBike = bike.clone()
    val advancedBike = bike.clone().makeAdvanced()

    println(bike)
    println(basicBike)
    println(advancedBike)
}

