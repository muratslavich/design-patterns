package creational.AbstractFactory.GuiFactory

import creational.AbstractFactory.Button.*
import creational.AbstractFactory.CheckBox.*

interface GuiFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox
}