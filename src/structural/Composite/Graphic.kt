package structural.Composite

// hierarchy
open class Equipment(open val price: Int, name: String)
class Processor: Equipment(1070, "structural.Composite.Processor")
class HardDrive: Equipment(250, "Hard")
class Memory: Equipment(280, "structural.Composite.Memory")

// composite
open class Composite(name: String): Equipment(0, name) {
    private val equipments = ArrayList<Equipment>()
    override val price: Int
        get() = equipments.map { it.price }.sum()

    fun add(equipment: Equipment) = apply { equipments.add(equipment) }
}

class PersonalComputer: Composite("PC")


fun main() {
    val pc = PersonalComputer()
        .add(Processor())
        .add(HardDrive())
        .add(Memory())

    print(pc.price)
}