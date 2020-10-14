package structural.decorator

interface CoffeeMachine {
    fun makeSmallCoffee()
    fun makeLargeCoffee()
}

class NormalCoffeeMachine : CoffeeMachine {
    override fun makeSmallCoffee() = println("Normal small coffee")
    override fun makeLargeCoffee() = println("Normal large coffee")
}

class EnhancedCoffeeMachine(private val coffeeMachine: CoffeeMachine) : CoffeeMachine by coffeeMachine {

    override fun makeSmallCoffee() {
        println("Enhanced small coffee")
    }

    fun makeCoffeeWithMilk() {
        makeSmallCoffee()
        addMilk()
        println("Enhanced small coffee with milk")
    }

    private fun addMilk() {}
}

fun main() {
    val normalCoffeeMachine = NormalCoffeeMachine()
    val enhancedCoffeeMachine = EnhancedCoffeeMachine(normalCoffeeMachine)

    enhancedCoffeeMachine.makeSmallCoffee()
    enhancedCoffeeMachine.makeLargeCoffee()
    enhancedCoffeeMachine.makeCoffeeWithMilk()
}