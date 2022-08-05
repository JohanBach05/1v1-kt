package edu.bach.kotlin

typealias Choice = Int

typealias Chance = Int

class Player(val cg: ChanceGenerator, val config: Config) {

    fun skipTurn() { 
        println("Vous passez votre tour...")
    }
    

    fun shouldTurnBeSkipped(state: State): State {
        skipTurn()
        return state.copy(skip_turn = false)
    }

    
    fun input(): Choice { 
        println("Souhaitez-vous attaquer (1) ou utiliser une potion (2)? ")
        var choice: Choice?
        do {
            choice = readLine()?.toIntOrNull()
        } while (choice == null)
        return choice
    } 
    
    fun dodged() {
        println("L'ennemi a esquivé  votre attaque...")
    }


    fun dodge(): Chance { 
        val dodgeChance = cg.generate(config.lowerPlayerDodgeChance..config.upperPlayerDodgeChance)
        return dodgeChance
    }
    

    fun attack(state: State): State { 
        val yourAttack = cg.generate(config.lowerPlayerAttack..config.upperPlayerAttack)
        val newEnemyHealth = state.enemy_health - yourAttack
        val updatedEnemyHealth = state.copy(enemy_health = newEnemyHealth)
        return updatedEnemyHealth 
    }
    
    
    fun potions(state: State): State {
        val updatedNumberOfPotions = state.number_of_potions - 1
        println("Il vous en reste $updatedNumberOfPotions.")
        return state.copy(number_of_potions = updatedNumberOfPotions)
    }


    fun heal(state: State): State { 
        val healing = cg.generate(config.lowerPlayerHeal..config.upperPlayerHeal)
        val playerHealed = state.player_health + healing
        val updatedPlayerHealed = state.copy(player_health = playerHealed)
        println("Vous vous êtes soigné de $healing points de vie ")
        return updatedPlayerHealed
    } 

    fun returnSkipTurn(state: State): State {
        val updatedSkipTurn = state.copy(skip_turn = true)
        return updatedSkipTurn
    }


    fun win() { 
        println("Vous avez gagné! ")
    }


    fun lose() {
        println("Vous avez perdu... ")
    }
}