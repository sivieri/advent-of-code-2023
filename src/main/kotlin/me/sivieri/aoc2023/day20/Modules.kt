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
    abstract fun copy(): Module
    abstract fun registerPulseListener(listener: (sender: String, pulse: Pulse) -> Unit)
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

    override fun copy(): Module = FlipFlopModule(name, destinations)

    override fun registerPulseListener(listener: (sender: String, pulse: Pulse) -> Unit) = throw NotImplementedError()

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
    private var listener: (sender: String, pulse: Pulse) -> Unit = { _, _ -> }

    override fun response(sender: String, pulse: Pulse): Pulse {
        if (!latest.containsKey(sender)) throw IllegalArgumentException("Unknown sender")
        latest[sender] = pulse
        val resultingPulse = if (latest.values.all { it == Pulse.HIGH }) Pulse.LOW
        else Pulse.HIGH
        listener(name, resultingPulse)
        return resultingPulse
    }

    override fun copy(): Module = ConjunctionModule(name, inputs, destinations)

    override fun registerPulseListener(listener: (sender: String, pulse: Pulse) -> Unit) {
        this.listener = listener
    }

    override fun toString(): String {
        return "ConjunctionModule(name=$name,inputs=$inputs,destinations=$destinations)"
    }
}

class BroadcastModule(
    destinations: List<String>
): Module("broadcaster", destinations) {
    override fun response(sender: String, pulse: Pulse): Pulse = pulse

    override fun copy(): Module = BroadcastModule(destinations)

    override fun registerPulseListener(listener: (sender: String, pulse: Pulse) -> Unit) = throw NotImplementedError()

    override fun toString(): String {
        return "BroadcastModule(name=$name,destinations=$destinations)"
    }
}

class DebugModule(
    name: String
): Module(name, emptyList()) {
    var lows: Int = 0
    var highs: Int = 0

    override fun response(sender: String, pulse: Pulse): Pulse? {
        //println("Debug module $name received $pulse from $sender")
        if (pulse == Pulse.LOW) lows++
        if (pulse == Pulse.HIGH) highs++
        return null
    }

    override fun copy(): Module = DebugModule(name)

    override fun registerPulseListener(listener: (sender: String, pulse: Pulse) -> Unit) = throw NotImplementedError()

    override fun toString(): String {
        return "DebugModule(name=$name)"
    }
}
