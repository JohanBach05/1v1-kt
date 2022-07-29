// Variables
var player_health: Int = 50
var enemy_health: Int = 50
var number_of_potions: Int = 3
var skip_turn: Boolean = false


// Les règles
fun println_rules() {
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


// Jeu du joueur
fun player() {

    if (skip_turn) {
        println("Vous passez votre tour...")
        skip_turn = false
    }

    // Choix du joueur
    else {
        println("Souhaitez-vous attaquer (1) ou utiliser une potion (2) ? ")
        val user_choice: Int = Integer.valueOf(readLine())

        // Attacking
        if (user_choice == 1) {
            val dodge = (1..12).random()
            if (dodge == 1) {
                println("L'ennemi a ésquivé votre attaque...")
            }
            else {
                val your_attack = (5..10).random()
                enemy_health -= your_attack
                println("Vous avez infligé " + your_attack + " points de dégats.") 
            }
        }

        // Healing
        else if (user_choice == 2) {
            if (number_of_potions > 0) {
                val heal = (15..50).random()
                player_health += heal
                number_of_potions -= 1
                skip_turn = true
                println("Vous vous êtes soigné de " + heal + " points de vie.")
            }
            /*else {
                println("Vous n'avez plus de potion...")
                continue
            }*/
        }
    }

        // Victoire
    if (enemy_health <= 0) {
        println("Vous avez gagné !")     
    }
}


// Jeu de l'ennemi
fun enemy() {

    // Ennemy attack
    val enemy_dodge = (1..8).random()
    if (enemy_dodge == 1) {
        println("Vous avez esquivé l'attaque ennemie !")
    }
    else {
        val enemy_attack = (5..15).random()
        player_health -= enemy_attack
        println("L'ennemi vous a infligé " + enemy_attack + " points de dégats.")
    }

    // Défaite
    if (player_health <= 0) {
        println("Vous avez perdu...")
    }
}


// Affichage
fun display() {
    println("Il vous reste " + player_health + " points de vie.")
    println("Il reste " + enemy_health + " points de vie à l'ennemi.")
    for (i in 1..50) {
        print("-")
    }
    println()
}

// Main
fun main() {

    println_rules()

    while (true) {

        player()

        enemy()

        display()

        if (player_health <= 0) {
            break
        }

        if (enemy_health <= 0) {
            break
        }
    }
}