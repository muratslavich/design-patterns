package creational.Builder

class BankAccount(builder: BankAccount.Builder) {
    private val accountNumber: Double?
    private val owner: String?
    private val branch: String?
    private val balance: Double?
    private val interestRate: Double?

    init {
        accountNumber = builder.accountNumber
        owner = builder.owner
        branch = builder.branch
        balance = builder.balance
        interestRate = builder.interestRate
    }

    class Builder {
        var accountNumber: Double? = null
        var owner: String? = null
        var branch: String? = null
        var balance: Double? = null
        var interestRate: Double? = null

        fun accountNumber(accountNumber: Double) = apply { this.accountNumber = accountNumber }
        fun owner(owner: String) = apply { this.owner = owner }
        fun branch(branch: String) = apply { this.branch = branch }
        fun balance(balance: Double) = apply { this.balance = balance }
        fun interestRate(interestRate: Double) = apply { this.interestRate = interestRate }
        fun build() = BankAccount(this)
    }

    override fun toString(): String {
        return "BankAccount(accountNumber=$accountNumber, owner=$owner, branch=$branch, balance=$balance, interestRate=$interestRate)"
    }


}

class KotlinBankAccount(
    private val accountNumber: Double?,
    private val owner: String?,
    private val branch: String?,
    private val balance: Double?,
    private val interestRate: Double?
) {
    data class Builder(
        private var accountNumber: Double? = null,
        private var owner: String? = null,
        private var branch: String? = null,
        private var balance: Double? = null,
        private var interestRate: Double? = null
    ) {
        fun accountNumber(accountNumber: Double) = apply { this.accountNumber = accountNumber }
        fun owner(owner: String) = apply { this.owner = owner }
        fun branch(branch: String) = apply { this.branch = branch }
        fun balance(balance: Double) = apply { this.balance = balance }
        fun interestRate(interestRate: Double) = apply { this.interestRate = interestRate }
        fun build() = KotlinBankAccount(accountNumber, owner, branch, balance, interestRate)
    }

    override fun toString(): String {
        return "KotlinBankAccount(accountNumber=$accountNumber, owner=$owner, branch=$branch, balance=$balance, interestRate=$interestRate)"
    }
}
