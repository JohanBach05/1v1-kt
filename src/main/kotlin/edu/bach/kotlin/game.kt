package edu.bach.kotlin

class Game {
    
    val cg = ChanceGenerator()
    val config = Config()
    val jeuDuJoueur = Player(cg, config)
    val jeuEnnemi = Enemy(cg, config)
    var state = State()

    fun start() {

        Display.printRules()

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

            Display.printInfos(state)

            if (state.player_health <= 0) {
                jeuDuJoueur.lose()
                break
            }
        }
    }
}