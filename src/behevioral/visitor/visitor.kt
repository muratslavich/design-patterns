package behevioral.visitor

import java.util.*


interface Shape {
    fun move(x: Int, y: Int)
    fun draw()
    fun accept(visitor: Visitor): String
}

open class Dot(var id: Int, var x: Int, var y: Int) : Shape {
    override fun move(x: Int, y: Int) { }
    override fun draw() { }
    override fun accept(visitor: Visitor): String = visitor.visitDot(this)
}

class Circle(id: Int, x: Int, y: Int, val radius: Int) : Dot(id, x, y) {
    override fun accept(visitor: Visitor): String = visitor.visitCircle(this)
}

class Rectangle(val id: Int, val x: Int, val y: Int, val width: Int, val height: Int) : Shape {
    override fun accept(visitor: Visitor) = visitor.visitRectangle(this)
    override fun move(x: Int, y: Int) { }
    override fun draw() { }

}

class CompoundShape(var id: Int) : Shape {
    var children: MutableList<Shape> = ArrayList()
    override fun move(x: Int, y: Int) { }
    override fun draw() { }
    override fun accept(visitor: Visitor) = visitor.visitCompoundGraphic(this)

    fun add(shape: Shape) {
        children.add(shape)
    }
}

interface Visitor {
    fun visitDot(dot: Dot): String
    fun visitCircle(circle: Circle): String
    fun visitRectangle(rectangle: Rectangle): String
    fun visitCompoundGraphic(cg: CompoundShape): String
}

class XMLExportVisitor : Visitor {
    fun export(vararg args: Shape): String {
        val sb = StringBuilder()
        sb.append("""<?xml version="1.0" encoding="utf-8"?>""")
        for (shape in args) {
            sb.append(shape.accept(this)).append("\n")
        }
        return sb.toString()
    }

    override fun visitDot(d: Dot) = """<dot>
        <id>${d.id}</id>
        <x>${d.x}</x>
        <y>${d.y}</y>
        </dot>"""

    override fun visitCircle(c: Circle) = """<circle>
        <id>${c.id}</id>
        <x>${c.x}</x>
        <y>${c.y}</y>
        <radius>${c.radius}</radius>
        </circle>"""

    override fun visitRectangle(r: Rectangle) = """<rectangle>
        <id>${r.id}</id>
        <x>${r.x}</x>
        <y>${r.y}</y>
        <width>${r.width}</width>
        <height>${r.height}</height>
        </rectangle>"""

    override fun visitCompoundGraphic(cg: CompoundShape) = """<compound_graphic>
        <id>${cg.id}</id>
        ${_visitCompoundGraphic(cg)}</compound_graphic>"""

    private fun _visitCompoundGraphic(cg: CompoundShape): String {
        val sb = StringBuilder()
        for (shape in cg.children) {
            var obj = shape.accept(this)
            // Proper indentation for sub-objects.
            obj = """    ${obj.replace("\n", "\n    ")}
            """.trimMargin()
            sb.append(obj)
        }
        return sb.toString()
    }
}

fun main() {
    val dot = Dot(1, 10, 55)
    val circle = Circle(2, 23, 15, 10)
    val rectangle = Rectangle(3, 10, 17, 20, 30)

    val compoundShape = CompoundShape(4)
    compoundShape.add(dot)
    compoundShape.add(circle)
    compoundShape.add(rectangle)

    val c = CompoundShape(5)
    c.add(dot)
    compoundShape.add(c)

    val exportVisitor = XMLExportVisitor()
    println(exportVisitor.export(circle, compoundShape))
}