package creational.FactoryMethod.Dialog

import creational.FactoryMethod.Button.*

class WindowDialog : Dialog() {
    override fun createButton(): Button {
        return WindowButton()
    }
}