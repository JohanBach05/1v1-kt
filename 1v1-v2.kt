typealias Choice = Int

typealias Chance = Int


data class State( 
    val player_health: Int = 50, 
    val enemy_health: Int = 50, 
    val number_of_potions: Int = 3,
    val skip_turn: Boolean = false
)


class Player {

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
        val dodgeChance = (1..12).random()  
        return dodgeChance
        }
    

    fun attack(state: State): State { 
        val yourAttack = (5..10).random()
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
        val healing = (15..50).random() 
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


class Enemy {

    fun dodged() {
        println("Vous avez esquivé l'attaque ennemie! ")
    }


    fun dodge(): Chance {
        val enemyDodgeChance = (1..8).random()
        return enemyDodgeChance
    }


    fun attack(state: State): State {
        val enemyAttack = (5..15).random()
        val newPlayerHealth = state.player_health - enemyAttack
        val updatedPlayerHealth = state.copy(player_health = newPlayerHealth)
        return updatedPlayerHealth
    }
}


class Display {

    fun printInfos(state: State) {
        println("Il vous reste ${state.player_health} points de vie. ")
        println("Il reste ${state.enemy_health} points de vie à l'ennemi. ")
        for (i in 1..50) {
            print("=")
        }
        println()
    }
}


class Game {
        
    val jeuDuJoueur = Player()
    val jeuEnnemi = Enemy()
    val affichage = Display()
    var state = State()


    fun printRules() {
        println("""
        Voici les regles:
        - Vous et votre ennemi avez 50PV.  
        - A chaque tour vous pouvez attaquer votre ennemi en lui infligeant entre 5 et 10 points de degats. 
        - Vous pouvez egalement vous soigner 15 a 50 PV en utilisant une potion, mais attention vous n'avez que 3 potions et si vous en utilisez une, vous passerez le tour suivant.  
        - Votre ennemi n'a pas de potion.  
        - Votre ennemi inflige entre 5 et 15 points de degats  
        - Bonne chance ! 
        """) 
    }


    fun start() {

        printRules()

        while (true) {

            if (state.skip_turn) {

                state = jeuDuJoueur.shouldTurnBeSkipped(state)
            }

            else {

                val userChoice = jeuDuJoueur.input()

                if (userChoice == 1) {

                    val dodgeChance = jeuDuJoueur.dodge()

                    if (dodgeChance == 1) {

                        jeuDuJoueur.dodged()
                    }
                    else {
                        state = jeuDuJoueur.attack(state) 
                    }

                }

                else if (userChoice == 2) {
                    if (state.number_of_potions > 0) {

                    state = jeuDuJoueur.potions(state)

                    state = jeuDuJoueur.heal(state)

                    state = jeuDuJoueur.returnSkipTurn(state)
                    }
                }
            }

            if (state.enemy_health <= 0) {
                jeuDuJoueur.win()
                break
            }

            jeuEnnemi.dodge()

            val enemyDodgeChance = jeuEnnemi.dodge()
            
            if (enemyDodgeChance == 1) {
                jeuEnnemi.dodged()
            }

            else {
                state = jeuEnnemi.attack(state)
            }

            affichage.printInfos(state)

            if (state.player_health <= 0) {
                jeuDuJoueur.lose()
                break
            }
        }
    }
}

fun main() {
    Game().start()
}