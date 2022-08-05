package edu.bach.kotlin

object Display {

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


    fun printInfos(state: State) {
        println("Il vous reste ${state.player_health} points de vie. ")
        println("Il reste ${state.enemy_health} points de vie à l'ennemi. ")
        for (i in 1..50) {
            print("=")
        }
        println()
    }
}