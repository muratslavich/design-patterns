package structural.Flyweight

// This class contain part of tree describing. It isn`t unique for each tree
// so it extract to own abstraction with container
// It helps reduce tree abstraction
class TreeType(val name: String, val color: Int, val texture: String) {
    fun draw(canvas: String, x: Int, y: Int) = println("TreeType(name='$name', color=$color, texture='$texture')")
}

class TreeFactory {

    companion object {
        private val treeTypes = mutableListOf<TreeType>()

        fun getTreeType(name: String, color: Int, texture: String): TreeType =
            treeTypes.find { it.name == name && it.color == color && it.texture == texture }
                ?: treeTypes.plus(TreeType(name, color, texture)).last()
    }
}

class Tree(private val type: TreeType, private val x: Int, private val y: Int) {
    fun draw(canvas: String) {
        type.draw(canvas, x, y)
    }
}

class Forest {
    private val trees = mutableListOf<Tree>()

    fun plantTree(x: Int, y: Int, name: String, color: Int, texture: String) {
        val type = TreeFactory.getTreeType(name, color, texture)
        val tree = Tree(type, x, y)
        trees.add(tree)
    }

    fun draw(canvas: String) = trees.forEach{ it.draw(canvas) }
}

fun main() {
    val forest = Forest()

    forest.plantTree(1,2,"sosna", 1,"square")
    forest.plantTree(2,3,"bereza", 1,"square")

    forest.draw("canvas")
}
