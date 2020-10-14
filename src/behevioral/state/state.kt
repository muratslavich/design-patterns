package behevioral.state

import java.util.*


// Common interface for all states.
abstract class State(val player: Player) {
    abstract fun onLock(): String
    abstract fun onPlay(): String
    abstract fun onNext(): String
    abstract fun onPrevious(): String
}

class LockedState(player: Player) : State(player) {
    override fun onLock() = when {
        player.isPlaying -> {
            player.changeState(ReadyState(player))
            "Stop playing"
        }
        else -> {
            "Locked..."
        }
    }

    override fun onPlay(): String {
        player.changeState(ReadyState(player))
        return "Ready"
    }

    override fun onNext() = "Locked..."
    override fun onPrevious() = "Locked..."
}

class ReadyState(player: Player) : State(player) {
    override fun onLock(): String {
        player.changeState(LockedState(player))
        return "Locked..."
    }

    override fun onPlay(): String {
        player.changeState(PlayingState(player))
        return player.startPlayback()
    }

    override fun onNext() = "Locked..."
    override fun onPrevious() = "Locked..."
}

class PlayingState(player: Player) : State(player) {
    override fun onLock(): String {
        player.changeState(LockedState(player))
        player.setCurrentTrackAfterStop()
        return "Stop playing"
    }

    override fun onPlay(): String {
        player.changeState(ReadyState(player))
        return "Paused..."
    }

    override fun onNext(): String = player.nextTrack()
    override fun onPrevious(): String = player.previousTrack()
}

class Player {
    var state: State = ReadyState(this)
    var isPlaying = true
    private val playlist: MutableList<String> = ArrayList()
    private var currentTrack = 0

    init {
        for (i in 1..12) {
            playlist.add("Track $i")
        }
    }

    fun changeState(state: State) {
        this.state = state
    }

    fun startPlayback() = "Playing " + playlist[currentTrack]

    fun nextTrack(): String {
        currentTrack++
        if (currentTrack > playlist.size - 1) {
            currentTrack = 0
        }
        return "Playing " + playlist[currentTrack]
    }

    fun previousTrack(): String {
        currentTrack--
        if (currentTrack < 0) {
            currentTrack = playlist.size - 1
        }
        return "Playing " + playlist[currentTrack]
    }

    fun setCurrentTrackAfterStop() {
        currentTrack = 0
    }
}

fun main() {
    val player = Player()
    player.apply {
        println(state.onPlay())
        println(state.onNext())
        println(state.onNext())
        println(state.onNext())
        println(state.onPrevious())
        println(state.onLock())
    }
}