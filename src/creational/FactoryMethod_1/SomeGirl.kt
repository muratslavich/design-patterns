package creational.FactoryMethod_1

class SomeGirl private constructor(
    val age: Int,
    val name: String? = null,
    val desires: String? = null
) {
    companion object {
        fun newGirl(vararg desires: String) = SomeGirl(17, desires =  desires.joinToString(", "))
        fun newGirl(name: String) = SomeGirl(17, name =  name)
    }

    override fun toString(): String {
        return "SomeGirl(age=$age, name=$name, desires=$desires)"
    }
}



