package me.sivieri.aoc2023.day20

class Circuit(input: List<String>) {

    private val modules: List<Module>

    init {
        val no = input.associate {
            val (name, destinations) = it.split(" -> ")
            val actualName = name.trim('%', '&')
            actualName to Pair(name, destinations.split(", "))
        }
        val r = no.map { (name, pairs) ->
            val (ns, destinations) = pairs
            val inputs = no.filter { it.value.second.contains(name) }.keys.toList()
            when (ns[0]) {
                '%' -> FlipFlopModule(name, destinations)
                '&' -> ConjunctionModule(name, inputs, destinations)
                'b' -> BroadcastModule(destinations)
                else -> throw IllegalArgumentException("Unknown module ${ns[0]}")
            }
        }
        val debugs = no
            .values
            .map { it.second }
            .flatten()
            .subtract(r.map { it.name })
            .toSet()
            .map { DebugModule(it) }
        modules = r + debugs
    }

    fun countPulses(times: Int): Pair<Int, Int> = (1..times).fold(Pair(0, 0)) { acc, _ ->
        val (low, high) = pulse()
        Pair(acc.first + low, acc.second + high)
    }

    private fun pulse(): Pair<Int, Int> {
        val broadcaster = modules.find { it is BroadcastModule }!!
        var lows = 1 + broadcaster.destinations.size
        var highs = 0
        val pulse = broadcaster.response("button", Pulse.LOW)!!
        var messages = broadcaster.destinations.map {
            CircuitMessage(broadcaster.name, it, pulse)
        }
        while (messages.isNotEmpty()) {
            messages = messages.flatMap { msg ->
                modules.fold(emptyList()) { acc, m ->
                    if (m.name == msg.destination) {
                        val v = m
                            .response(msg.sender, msg.pulse)
                            ?.let { p ->
                                if (p == Pulse.LOW) lows += m.destinations.size
                                else highs += m.destinations.size
                                m.destinations.map {
                                    CircuitMessage(m.name, it, p)
                                }
                            } ?: emptyList()
                        acc + v
                    }
                    else acc
                }
            }
        }
        return Pair(lows, highs)
    }

}