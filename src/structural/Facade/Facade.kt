package structural.Facade

class ComplexSystem(private val filePath: String) {
    private val cache: HashMap<String, String>

    init {
        println("reading data from $filePath")
        cache = HashMap()
    }

    fun store(key: String, payload: String) {
        cache[key] = payload
    }

    fun read(key: String): String = cache[key] ?: ""

    fun commit() = println("Storing cached data: $cache to file: $filePath")
}

data class User(var login: String)

class UserRepository {
    private val systemPreferences = ComplexSystem("/data/default.prefs")

    fun save(user: User) {
        systemPreferences.store("User_key", user.login)
        systemPreferences.commit()
    }

    fun findFirst(): User = User(systemPreferences.read("User_key"))
}

fun main() {
    val userRepository = UserRepository()
    val user = User("murat")
    userRepository.save(user)
    val result = userRepository.findFirst()

    println("$result")
}