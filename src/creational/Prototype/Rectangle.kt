package creational.Prototype

class Rectangle : Shape {

    var width: Int?
    var height: Int?

    constructor(width: Int? = null, height: Int? = null) : super() {
        this.width = width
        this.height = height
    }

    constructor(source: Rectangle) : super(source) {
        this.width = source.width
        this.height = source.height
    }

    override fun clone(): Shape = Rectangle(this)

    override fun toString(): String {
        val superString = super.toString()
        return "$superString Rectangle(width=$width, height=$height)"
    }


}

fun main() {

    val rectangle = Rectangle()
    rectangle.width = 10
    rectangle.height = 20

    val shapes = mutableListOf(rectangle, rectangle.clone(), rectangle.clone())

    shapes.forEach{ println(it) }
}