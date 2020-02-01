package creational.AbstractFactory.GuiFactory

import creational.AbstractFactory.Button.*
import creational.AbstractFactory.CheckBox.*

class MacFactory: GuiFactory {
    override fun createButton(): Button {
        return MacButton()
    }

    override fun createCheckbox(): Checkbox {
        return MacChecbox()
    }
}