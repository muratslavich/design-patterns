package creational.Builder

fun main() {
    var bankAccount = BankAccount.Builder()
        .accountNumber(12.0)
        .balance(200.0)
        .branch("develop")
        .build()

    val kotlinBankAccount = KotlinBankAccount.Builder()
        .accountNumber(1222.0)
        .build()

    println(bankAccount)
    println(kotlinBankAccount)
}