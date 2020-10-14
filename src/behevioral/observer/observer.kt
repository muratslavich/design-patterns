package behevioral.observer

import java.io.File

interface EventListener {
    fun update(eventType: String, file: File)
}

class EmailNotificationListener(private val email: String) : EventListener {
    override fun update(eventType: String, file: File) {
        println("Email to  $email: Someone has performed $eventType operation with the following file: ${file.name}")
    }
}

class LogOpenListener(fileName: String) : EventListener {
    private val log = File(fileName)

    override fun update(eventType: String, file: File) {
        println("Save to log $log: Someone has performed $eventType operation with the following file: ${file.name}")
    }
}

class EventManager(vararg operations: String) {
    private val listeners: HashMap<String, MutableList<EventListener>> = HashMap()

    init {
        operations.forEach { listeners[it] = mutableListOf() }
    }

    fun subscribe(eventType: String, listener: EventListener) = listeners[eventType]?.add(listener)
    fun unsubscribe(eventType: String, listener: EventListener) = listeners[eventType]?.remove(listener)
    fun notify(eventType: String, file: File) = listeners[eventType]?.forEach { it.update(eventType, file) }
}

class Editor {
    val events = EventManager("open", "save")
    private var file: File? = null

    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify("open", file!!)
    }

    fun saveFile() {
        events.notify("save", file!!)
    }
}

fun main() {
    val editor = Editor()
    editor.events.subscribe("open", LogOpenListener("/path/to/log/file.txt"))
    editor.events.subscribe("save", EmailNotificationListener("admin@example.com"))

    editor.openFile("test.txt")
    editor.saveFile()
}