package edu.bach.kotlin

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "attack() should return the health of the enemy" {
        val state = State()
        val player = Player(MockedChanceGenerator(), Config())
        player.attack(state) shouldBe State(
            player_health = 50,
            enemy_health = 45,
            number_of_potions = 3,
            skip_turn = false)
    }
})

class MockedChanceGenerator : ChanceGenerator() {
    override fun generate(range: IntRange): Chance {
        return 5
    }
}