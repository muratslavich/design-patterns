package creational.FactoryMethod

import creational.FactoryMethod.Dialog.*

fun main() {
    val config = "OSs"

    val dialog: Dialog
    if (config == "OS")
        dialog = WindowDialog()
    else
        dialog = WebDialog()

    dialog.render()
}