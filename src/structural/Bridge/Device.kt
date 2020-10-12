package structural.Bridge

// Implementation layer
interface Device {
    var isEnabled: Boolean
    var volume: Int
}

class Tv(override var isEnabled: Boolean = false, override var volume: Int = 0) : Device
class Radio(override var isEnabled: Boolean = true, override var volume: Int = 10) : Device

// Abstraction layer
class Remote(val device: Device) {

    fun togglePower() {
        device.isEnabled = !device.isEnabled
    }

    fun volumeUp() = run { device.volume += 10 }
    fun volumeDown() = run { device.volume -= 10 }

}


fun main() {
    val tv = Tv()
    val radio = Radio()

    val remote = Remote(tv) // this is Bridge (aggregation over inheritance)
    remote.togglePower()

    print("${tv.isEnabled} ${tv.volume}")
}

