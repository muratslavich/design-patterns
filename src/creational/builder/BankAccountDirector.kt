package creational.builder

class BankAccountDirector(private val builder: BankAccount.Builder) {

    fun constructEmptyAccount() = builder.build()
    fun constructAccountWithBalance() = builder.balance(100.0).build()

}

fun main() {
    val director = BankAccountDirector(BankAccount.Builder())
    val accountWithBalance = director.constructAccountWithBalance()

    print(accountWithBalance)
}
