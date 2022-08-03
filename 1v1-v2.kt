data class State( 
    val player_health: Int = 50, 
    val enemy_health: Int = 50, 
    val number_of_potions: Int = 3,
    val skip_turn: Boolean = false
)


class Player {

    val state = State(50, 50, 3, false)


    fun shouldTurnBeSkipped() = false


    fun skipTurn() { 
        println("Vous passez votre tour...")
    }
    
    
    fun input(): Int { 
        println("Souhaitez-vous attaquer (1) ou utiliser une potion (2)? ")
        var choice: Int?
        do {
            choice = readLine()?.toIntOrNull()
        } while (choice == null)
        return choice
    } 
    
    fun dodged() {
        println("L'ennemi a esquivé  votre attaque...")
    }


    fun dodge() { 
        val dodgeChance = (1..12).random()  
        if (dodgeChance == 1) { 
            dodged()
        }
    } 
    
    
    fun attack(enemy_health: Int = variables.enemy_health) { 
        val yourAttack = (5..10).random()
        val  nEH = enemy_health - yourAttack
        val newEnemyHealth = variables.copy(enemy_health = nEH)
    } 
    
    
    fun heal(number_of_potions: Int = variables.number_of_potions, player_health: Int = variables.player_health ,skip_turn: Boolean = variables.skip_turn) { 
        if (number_of_potions > 0) { 
            val heal = (15..50).random() 
            val healed = player_health + heal
            val playerHealed = variables.copy(healed) 
            val number_of_potions = number_of_potions - 1
            val skip_turn = true 
        } 
    } 
    
    
    fun win(enemy_health: Int = variables.enemy_health) { 
        if (enemy_health <= 0) { 
            print("Vous avez gagné! ") 
        } 
    } 
}


class Enemy {

    val variables = Variables(50, 50, 3, false)

    fun dodged() {
        println("Vous avez esquivé l'attaque ennemie! ")
    }


    fun dodge() {
        val enemyDodgeChance = (1..8).random()
        if (enemyDodgeChance == 1) {
            dodged()
        }
    }


    fun attack(player_health: Int = variables.player_health) {
        val enemyAttack = (5..15).random()
        val nPH = player_health - enemyAttack
        val newPlayerHealth = variables.copy(player_health = nPH)
    }


    fun lose(player_health: Int = variables.player_health) {
        if (player_health <= 0) {
            print("Vous avez perdu... ")
        }
    }
}


class Game {
        
    var jeuDuJoueur = Player()
    var jeuEnnemi = Enemy()


    fun print_rules() {
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


    //val rules = print_rules()
    init {

        /*fun toBoolean(s: String): Boolean {
            return s.toBoolean()
        }*/
    

        while (true) {

            if (jeuDuJoueur.shouldTurnBeSkipped()) {

                jeuDuJoueur.skipTurn()
            }

            else {

                val userChoice = jeuDuJoueur.input()

                if (userChoice == 1) {

                    jeuDuJoueur.dodge()

                    jeuDuJoueur.attack() 
                }

                else if (userChoice == 2) {

                jeuDuJoueur.heal()
                }
            }

            jeuDuJoueur.win()

            jeuEnnemi.dodge()

            jeuEnnemi.attack()

            jeuEnnemi.lose()
    
        }        
    }
}


fun main() {

    Variables(50, 50, 3, false)

    Logic()
}