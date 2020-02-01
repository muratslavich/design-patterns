package creational.FactoryMethod.Dialog

import creational.FactoryMethod.Button.*

abstract class Dialog {
    fun render() {
        val someButton = createButton()
        someButton.render()
    }

    abstract fun createButton(): Button // Factory method
}

/*
* 1 Приведите все создаваемые продукты к общему интерфейсу.
* 2 В классе, который производит продукты, создайте пустой фабричный метод. В качестве возвращаемого типа укажите общий интерфейс продукта.
* 3 Для каждого типа продуктов заведите подкласс и переопределите в нём фабричный метод. Переместите туда код создания соответствующего продукта из суперкласса.
* */