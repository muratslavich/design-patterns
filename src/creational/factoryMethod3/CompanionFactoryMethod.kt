package creational.factoryMethod3

interface Dependency<T> {
    var mocked: T?
    fun get(): T
    fun lazyGet(): Lazy<T> = lazy { get() }
}

class Provider<T>(val init: ()->T): Dependency<T> {
    var original: T? = null
    override var mocked: T? = null

    override fun get(): T = mocked ?: original ?: init()
        .apply { original = this }
}

interface UserRepository {
    fun getUser(): User

    companion object: Dependency<UserRepository> by Provider({ UserRepositoryImpl() })
}

class UserRepositoryImpl : UserRepository {
    override fun getUser(): User = User("Aaron")
}

class User(var name: String)

fun main() {
    val userRepository = UserRepository.get()
    val lazyUser = UserRepository.lazyGet()

    println( userRepository.getUser().name )
    println( lazyUser.isInitialized() )

    UserRepository.mocked = object : UserRepository {
        override fun getUser(): User = User("mock")
    }
    println( UserRepository.mocked?.getUser()?.name )
}
