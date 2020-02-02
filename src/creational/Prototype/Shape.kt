package creational.Prototype

abstract class Shape(
    private var Y: Int? = null,
    private var X: Int? = null,
    private var color: String? = null
) {
    constructor(X: Int, Y: Int, color: String) : this() {
        this.X = X
        this.Y = Y
        this.color = color
    }
    constructor(source: Shape) : this(source.X, source.Y, source.color)
    abstract fun clone(): Shape

    override fun toString(): String {
        val superString = super.toString()
        return "$superString Shape(Y=$Y, X=$X, color=$color)"
    }


}