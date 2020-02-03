
open class Equipment(open val price: Int, name: String)

open class Composite(name: String): Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()

    override val price: Int
        get() = equipments.map { it.price }.sum()

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

class PersonalComputer: Composite("PC")

class Processor: Equipment(1070, "Processor")
class HardDrive: Equipment(250, "Hard")
class Memory: Equipment(280, "Memory")


fun main() {
    val pc = PersonalComputer()
        .add(Processor())
        .add(HardDrive())
        .add(Memory())

    print(pc.price)
}