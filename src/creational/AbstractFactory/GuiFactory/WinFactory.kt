package creational.AbstractFactory.GuiFactory

import creational.AbstractFactory.Button.*
import creational.AbstractFactory.CheckBox.*

class WinFactory: GuiFactory {
    override fun createButton(): Button {
        return WinButton()
    }

    override fun createCheckbox(): Checkbox {
        return WinCheckbox()
    }
}