package me.sivieri.aoc2023.day20

enum class Pulse {
    LOW,
    HIGH;
}

sealed class Module(
    val name: String,
    val destinations: List<String>
) {
    abstract fun response(sender: String, pulse: Pulse): Pulse?
}

class FlipFlopModule(
    name: String,
    destinations: List<String>
): Module(name, destinations) {
    private var status: Boolean = false

    override fun response(sender: String, pulse: Pulse): Pulse? {
        return if (pulse == Pulse.HIGH) null
        else {
            if (status) {
                status = false
                Pulse.LOW
            }
            else {
                status = true
                Pulse.HIGH
            }
        }
    }

    override fun toString(): String {
        return "FlipFlopModule(name=$name,destinations=$destinations)"
    }
}

class ConjunctionModule(
    name: String,
    val inputs: List<String>,
    destinations: List<String>
): Module(name, destinations) {
    private val latest = this
        .inputs
        .associateWith { Pulse.LOW }
        .toMutableMap()

    override fun response(sender: String, pulse: Pulse): Pulse {
        if (!latest.containsKey(sender)) throw IllegalArgumentException("Unknown sender")
        latest[sender] = pulse
        return if (latest.values.all { it == Pulse.HIGH }) Pulse.LOW
        else Pulse.HIGH
    }

    override fun toString(): String {
        return "ConjunctionModule(name=$name,inputs=$inputs,destinations=$destinations)"
    }
}

class BroadcastModule(
    destinations: List<String>
): Module("broadcaster", destinations) {
    override fun response(sender: String, pulse: Pulse): Pulse = pulse

    override fun toString(): String {
        return "BroadcastModule(name=$name,destinations=$destinations)"
    }
}

class DebugModule(
    name: String
): Module(name, emptyList()) {
    override fun response(sender: String, pulse: Pulse): Pulse? {
        println("Debug module $name received $pulse from $sender")
        return null
    }

    override fun toString(): String {
        return "DebugModule(name=$name)"
    }
}
