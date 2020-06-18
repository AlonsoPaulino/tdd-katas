import org.junit.Test

class MarsRoverTest {

    @Test
    fun `Given direction W when command L then direction is S`() {
        val rover = Rover(direction = Direction.W, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.L))
        assert(rover.direction == Direction.S)
    }

    @Test
    fun `Given direction S when command L then direction is E`() {
        val rover = Rover(direction = Direction.S, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.L))
        assert(rover.direction == Direction.E)
    }

    @Test
    fun `Given direction E when command L then direction is N`() {
        val rover = Rover(direction = Direction.E, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.L))
        assert(rover.direction == Direction.N)
    }

    @Test
    fun `Given direction N when command L then direction is W`() {
        val rover = Rover(direction = Direction.N, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.L))
        assert(rover.direction == Direction.W)
    }

    @Test
    fun `Given direction W when command R then direction is N`() {
        val rover = Rover(direction = Direction.W, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.R))
        assert(rover.direction == Direction.N)
    }

    @Test
    fun `Given direction S when command R then direction is W`() {
        val rover = Rover(direction = Direction.S, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.R))
        assert(rover.direction == Direction.W)
    }

    @Test
    fun `Given direction E when command R then direction is S`() {
        val rover = Rover(direction = Direction.E, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.R))
        assert(rover.direction == Direction.S)
    }

    @Test
    fun `Given direction N when command R then direction is E`() {
        val rover = Rover(direction = Direction.N, planet = provideMarsWithNoObstacles())
        rover.move(listOf(Command.R))
        assert(rover.direction == Direction.E)
    }

    @Test
    fun `Given (0, 0) when direction is E and command is F then (0, 1)`() {
        val rover = Rover(Pair(0, 0), Direction.E, provideMarsWithNoObstacles())
        rover.move(listOf(Command.F))
        assert(0 == rover.position.first)
        assert(1 == rover.position.second)
    }

    @Test
    fun `Given (0, 0) when direction is W and command is B then (0, 1)`() {
        val rover = Rover(Pair(0, 0), Direction.W, provideMarsWithNoObstacles())
        rover.move(listOf(Command.B))
        assert(0 == rover.position.first)
        assert(1 == rover.position.second)
    }

    @Test
    fun `Given (0, 0) when direction = E and command is {B, B, R, F} then (2, 1)`() {
        val rover = Rover(Pair(0, 0), Direction.E, provideMarsWithNoObstacles())
        rover.move(listOf(Command.B, Command.B, Command.R, Command.F))
        assert(1 == rover.position.first)
        assert(1 == rover.position.second)
    }

    @Test
    fun `Given (0, 0) when direction = N and command is {F, F, L, R, B} then (2, 0)`() {
        val rover = Rover(Pair(0, 0), Direction.N, provideMarsWithNoObstacles())
        rover.move(listOf(Command.F, Command.F, Command.L, Command.R, Command.B))
        assert(2 == rover.position.first)
        assert(0 == rover.position.second)
    }

    @Test
    fun `Given (0, 0) when direction = S and command is {B, B, F, L, R, B, F, R, L, F, B, R} then (2, 0)`() {
        val rover = Rover(Pair(0, 0), Direction.S, provideMarsWithNoObstacles())
        rover.move(
            listOf(
                Command.B,
                Command.B,
                Command.F,
                Command.L,
                Command.R,
                Command.B,
                Command.F,
                Command.R,
                Command.L,
                Command.F,
                Command.B,
                Command.R
            )
        )
        assert(2 == rover.position.first)
        assert(0 == rover.position.second)
    }

    @Test
    fun `Given (2, 1) when direction = E and command is {R, F, F, L, F, F, B, L, F, R, F} then (0, 0)`() {
        val rover = Rover(Pair(2, 1), Direction.E, provideMarsWithNoObstacles())
        rover.move(
            listOf(
                Command.R,
                Command.F,
                Command.F,
                Command.L,
                Command.F,
                Command.F,
                Command.B,
                Command.L,
                Command.F,
                Command.R,
                Command.F
            )
        )
        assert(0 == rover.position.first)
        assert(0 == rover.position.second)
    }

    @Test
    fun `Given (0, 0) when direction = E and command is {F, F, F} then (0, 1)`() {
        val rover = Rover(direction = Direction.E, planet = provideMarsWithObstacles())
        try {
            rover.move(
                listOf(Command.F, Command.F, Command.F)
            )
        } catch (exception: Exception) {
            assert(exception is ObstacleException)
            assert(2 == rover.position.first)
            assert(2 == rover.position.second)
        }
    }

    companion object {
        fun provideMarsWithNoObstacles(): Planet {
            return Planet(
                listOf(
                    listOf(false, false, false),
                    listOf(false, false, false),
                    listOf(false, false, false)
                )
            )
        }

        fun provideMarsWithObstacles(): Planet {
            return Planet(
                listOf(
                    listOf(false, false, true),
                    listOf(false, true, false),
                    listOf(false, true, false)
                )
            )
        }
    }
}