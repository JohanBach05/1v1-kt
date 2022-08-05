package edu.bach.kotlin

data class State( 
    val player_health: Int = 50, 
    val enemy_health: Int = 50, 
    val number_of_potions: Int = 3,
    val skip_turn: Boolean = false
)


data class Config (
    val lowerPlayerDodgeChance: Int = 1,
    val upperPlayerDodgeChance: Int = 12,
    val lowerPlayerAttack: Int = 5,
    val upperPlayerAttack: Int = 10,
    val lowerPlayerHeal: Int = 15,
    val upperPlayerHeal: Int = 50,
    val lowerEnemyDodgeChance: Int = 1,
    val upperEnemyDodgeChance: Int = 8,
    val lowerEnemyAttack: Int = 5,
    val upperEnemyAttack: Int = 15
)


class ChanceGenerator {

    fun generate(range: IntRange): Chance {
        return range.random().toInt()
    }
} 
