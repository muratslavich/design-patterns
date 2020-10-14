package creational.prototype

abstract class Shape(
    private var Y: Int? = null,
    private var X: Int? = null,
    private var color: String? = null
) {
    constructor(source: Shape) : this(source.X, source.Y, source.color)
    abstract fun clone(): Shape
}

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
}

fun main() {

    val rectangle = Rectangle()
    rectangle.width = 10
    rectangle.height = 20

    val shapes = mutableListOf(rectangle, rectangle.clone(), rectangle.clone())

    shapes.forEach{ println(it) }
}