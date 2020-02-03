package structural.Bridge

class Remote(val device: Device) {

    fun togglePower() {
        device.isEnabled = !device.isEnabled
    }

    fun volumeUp() = run { device.volume += 10 }
    fun volumeDown() = run { device.volume -= 10 }

}