package creational.AbstractFactory

interface Button {
    fun paint()
}

class MacButton: Button {
    override fun paint() = print("Mac style button")
}

class WinButton: Button {
    override fun paint() = print("Window style button")
}

interface Checkbox {
    fun paint()
}

class MacChecbox: Checkbox {
    override fun paint() = print("Mac style checkbox")
}

class WinCheckbox: Checkbox {
    override fun paint() = print("Windows style checkbox")
}

interface GuiFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox
}

class MacFactory: GuiFactory {
    override fun createButton(): Button = MacButton()
    override fun createCheckbox(): Checkbox = MacChecbox()
}

class WinFactory: GuiFactory {
    override fun createButton(): Button = WinButton()
    override fun createCheckbox(): Checkbox = WinCheckbox()
}

fun main() {
    val config = "WIN"

    val factory: GuiFactory = when (config) {
        "WIN" -> WinFactory()
        "MAC" -> MacFactory()
        else -> throw RuntimeException()
    }

    factory.apply {
        createButton().paint()
        createCheckbox().paint()
    }
}
