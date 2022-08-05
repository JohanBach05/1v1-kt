package edu.bach.kotlin

class Enemy(val cg: ChanceGenerator, val config: Config) {

    fun dodged() {
        println("Vous avez esquivé l'attaque ennemie! ")
    }


    fun dodge(): Chance {
        val enemyDodgeChance = cg.generate(config.lowerEnemyDodgeChance..config.upperEnemyDodgeChance)
        return enemyDodgeChance
    }


    fun attack(state: State): State {
        val enemyAttack = cg.generate(config.lowerEnemyAttack..config.upperEnemyAttack)
        val newPlayerHealth = state.player_health - enemyAttack
        val updatedPlayerHealth = state.copy(player_health = newPlayerHealth)
        return updatedPlayerHealth
    }
}