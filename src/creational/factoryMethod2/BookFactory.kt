package creational.factoryMethod2

enum class Genre {
    SCIENCE, LITERATURE
}

interface Book {
    fun getInfo(): String
    fun order(): String
    fun rate(): String
}


class BookFactory {
    companion object {
        fun createBook(genre: Genre): Book = when (genre) {
            Genre.SCIENCE -> object: Book {
                override fun getInfo() = "science"
                override fun order() = "123"
                override fun rate() = "M"
            }
            Genre.LITERATURE -> object: Book {
                override fun getInfo(): String = "literature"
                override fun order(): String = "321"
                override fun rate(): String = "A"
            }
        }
    }
}

fun main() {
    val book = BookFactory.createBook(Genre.SCIENCE)
    book.getInfo()
}
