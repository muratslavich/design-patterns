package creational.AbstractFactory

import creational.AbstractFactory.GuiFactory.*

fun main() {
    val config = "Win"

    val factory: GuiFactory
    if (config == "Win")
        factory = WinFactory()
    else
        factory = MacFactory()

    factory.createButton().paint()
    factory.createCheckbox().paint()
}

/*
* Все продукты к общим интерфейсам
* Создать конкретные фабрики реализующую абстрактную фабрику
* */