package behevioral.strategy

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


// Common interface for all strategies
interface PayStrategy {
    fun pay(paymentAmount: Int): Boolean
    fun collectPaymentDetails()
}

class PayByPayPal : PayStrategy {
    private val reader = BufferedReader(InputStreamReader(System.`in`))
    private var email: String? = null
    private var password: String? = null
    private var signedIn = false

    companion object {
        private val DATA_BASE: MutableMap<String, String> = HashMap()

        init {
            DATA_BASE["amanda1985"] = "amanda@ya.com"
            DATA_BASE["qwerty"] = "john@amazon.eu"
        }
    }

    /**
     * Collect customer's data.
     */
    override fun collectPaymentDetails() {
        try {
            while (!signedIn) {
                print("Enter the user's email: ")
                email = reader.readLine()
                print("Enter the password: ")
                password = reader.readLine()
                if (verify()) {
                    println("Data verification has been successful.")
                } else {
                    println("Wrong email or password!")
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    private fun verify(): Boolean {
        setSignedIn(email == DATA_BASE[password])
        return signedIn
    }

    /**
     * Save customer data for future shopping attempts.
     */
    override fun pay(paymentAmount: Int): Boolean = when {
        signedIn -> {
            println("Paying $paymentAmount using PayPal.")
            true
        }
        else -> false
    }

    private fun setSignedIn(signedIn: Boolean) {
        this.signedIn = signedIn
    }
}

class PayByCreditCard : PayStrategy {
    private val READER = BufferedReader(InputStreamReader(System.`in`))
    private var card: CreditCard? = null

    /**
     * Collect credit card data.
     */
    override fun collectPaymentDetails() {
        try {
            print("Enter the card number: ")
            val number = READER.readLine()
            print("Enter the card expiration date 'mm/yy': ")
            val date = READER.readLine()
            print("Enter the CVV code: ")
            val cvv = READER.readLine()
            card = CreditCard(number, date, cvv)

            // Validate credit card number...
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    /**
     * After card validation we can charge customer's credit card.
     */
    override fun pay(paymentAmount: Int): Boolean {
        return if (cardIsPresent()) {
            println("Paying $paymentAmount using Credit Card.")
            card!!.amount = card!!.amount - paymentAmount
            true
        } else {
            false
        }
    }

    private fun cardIsPresent(): Boolean {
        return card != null
    }
}

class CreditCard(
    private val number: String,
    private val date: String,
    private val cvv: String
) {
    var amount = 100000
}

class Order {
    var totalCost = 0
    var isClosed = false

    fun processOrder(strategy: PayStrategy) {
        strategy.collectPaymentDetails()
        // Here we could collect and store payment data from the strategy.
    }

    fun setClosed() {
        isClosed = true
    }
}

fun main() {
    val priceOnProducts: MutableMap<Int, Int> = hashMapOf(1 to 2200, 2 to 1850, 3 to 1100, 4 to 890)
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val order = Order()
    var strategy: PayStrategy? = null

    while (!order.isClosed) {
        var cost: Int
        var continueChoice: String
        do {
            print("""
                        Please, select a product:
                        1 - Mother board
                        2 - CPU
                        3 - HDD
                        4 - Memory
                        
                        """.trimIndent())
            val choice = reader.readLine().toInt()
            cost = priceOnProducts[choice]!!
            print("Count: ")
            val count = reader.readLine().toInt()
            order.totalCost = cost * count
            print("Do you wish to continue selecting products? Y/N: ")
            continueChoice = reader.readLine()
        } while (continueChoice.equals("Y", ignoreCase = true))
        if (strategy == null) {
            println("""
                        Please, select a payment method:
                        1 - PalPay
                        2 - Credit Card
                        """.trimIndent())

            // Client creates different strategies based on input from
            // user, application configuration, etc.
            // пользовательских данных, конфигурации и прочих параметров.
            strategy = when (reader.readLine()) {
                "1" -> PayByPayPal()
                else -> PayByCreditCard()
            }
        }

        // Order object delegates gathering payment data to strategy
        // object, since only strategies know what data they need to
        // process a payment.
        // т.к. только стратегии знают какие данные им нужны для приёма
        // оплаты.
        order.processOrder(strategy)
        print("Pay ${order.totalCost} units or Continue shopping? P/C: ")
        val proceed = reader.readLine()
        if (proceed.equals("P", ignoreCase = true)) {
            // Finally, strategy handles the payment.
            if (strategy.pay(order.totalCost)) {
                println("Payment has been successful.")
            } else {
                println("FAIL! Please, check your data.")
            }
            order.setClosed()
        }
    }
}