package creational.factoryMethod

import java.lang.RuntimeException

interface Button {
    fun render()
    fun onClick()
}

class HtmlButton : Button {
    override fun render() = print("I am HtmlButton")
    override fun onClick() = print("clicked HtmlButton")
}

class WindowButton : Button {
    override fun render() = print("I am WindowButton")
    override fun onClick() = print("clicked WindowButton")
}

abstract class Dialog {
    fun render() {
        val someButton = createButton()
        someButton.render()
    }

    abstract fun createButton(): Button // Factory method
}

class WebDialog : Dialog() {
    override fun createButton() = HtmlButton()
}

class WindowDialog : Dialog() {
    override fun createButton() = WindowButton()
}

fun main() {
    val config = "OS"

    val dialog = when (config) {
        "OS" -> WindowDialog()
        "WEB" -> WebDialog()
        else -> throw RuntimeException()
    }

    dialog.render()
}