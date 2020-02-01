package creational.FactoryMethod.Dialog

import creational.FactoryMethod.Button.*

class WebDialog : Dialog() {
    override fun createButton(): Button {
        return HtmlButton()
    }
}