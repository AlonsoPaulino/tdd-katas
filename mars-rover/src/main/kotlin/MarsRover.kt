import java.lang.Exception

data class Rover(
    var position: Pair<Int, Int> = Pair(0, 0),
    var direction: Direction,
    var planet: Planet
) {

    fun move(commands: List<Command>) {
        commands.forEach { command ->
            when (command) {
                Command.L -> {
                    direction = Direction.values()[(direction.ordinal - 1).myMod(Direction.values().size)]
                }
                Command.R -> {
                    direction = Direction.values()[(direction.ordinal + 1).myMod(Direction.values().size)]
                }
                Command.F -> {
                    position = if (direction == Direction.N || direction == Direction.S) {
                        Pair(position.first+ direction.factor, position.second)
                    } else {
                        Pair(position.first, position.second + direction.factor)
                    }
                }
                Command.B -> {
                    position = if (direction == Direction.N || direction == Direction.S) {
                        Pair(position.first - direction.factor, position.second)
                    } else {
                        Pair(position.first, position.second - direction.factor)
                    }
                }
            }

            position = Pair(position.first.myMod(planet.height), position.second.myMod(planet.width))

            if (planet.grid[position.first][position.second]) {
                throw ObstacleException("obstacle found at ${position.first}, ${position.second}").also {
                    val safeSpot = planet.lastSafePoint ?: position
                    position = safeSpot
                }
            }
        }
    }
}

class ObstacleException(message: String) : Exception(message)

data class Planet(val grid: List<List<Boolean>>) {
    val height get() = grid.size

    val width get() = if (grid.isNotEmpty()) grid.first().size else 0

    val lastSafePoint: Pair<Int, Int>? by lazy {
        for (i in height - 1..0) {
            for (j in width - 1..0) {
                if (!grid[i][j]) return@lazy Pair(i, j)
            }
        }
        return@lazy null
    }
}

enum class Direction(val factor: Int) {
    N(-1), E(1), S(1), W(-1)
}

enum class Command {
    F, B, L, R
}

fun Int.myMod(m: Int): Int {
    return (this % m + m) % m
}

