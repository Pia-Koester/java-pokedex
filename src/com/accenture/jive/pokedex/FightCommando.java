package com.accenture.jive.pokedex;

import com.accenture.jive.pokedex.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class FightCommando {

    public void execute(Pokemon opponent, ArrayList<Pokemon> pokedex, Scanner scanner) {
        System.out.println("\u001B[31m" + "oh no.... a wild " + opponent.name + " appeared." + "\u001B[0m");
        System.out.println("Who should fight this Pokemon? Select: ");
        for (Pokemon pokemon : pokedex) {
            System.out.println(pokemon.name);
        }
        String selectedPokemon = scanner.nextLine();
        Pokemon fightingPokemon = new Pokemon();
        //Im Pokedex wird gecheckt ob das eingegebene Pokemon existiert
        //QUESTION: ich hätte hier auch gerne die Exit Bedingugn ohne sie nochmal von vorne schreiben zu müssen

//TO DO: Double check dass das correct verläuft
        for (Pokemon pokemon : pokedex) {
            if (selectedPokemon.equals(pokemon.name)) {
                fightingPokemon = pokemon;
                System.out.println("You have selected " + pokemon.name + " to fight against " + opponent.name);
                break;
            } else {
                System.out.println("This pokemon is not part of your pokedex.");
            }
        }

        System.out.println("Select the attack from " + fightingPokemon.name + "s moveset.");
        //QUESTION: Ich zeige mehrfach alle moves aus dem moveset. Wie kann ich das refactoren, dass es nur einmal geschrieben ist?
        //Mal bezieht es sich auf ein spezifisches Pokemon und mal auf alle moves ...
        for (Move move : fightingPokemon.moveset) {
            System.out.println(move.name + " : " + move.power);
        }
        String inputMove = scanner.nextLine();
        boolean found = false;
        for (Move move : fightingPokemon.moveset) {
            if (inputMove.equalsIgnoreCase(move.name)) {
                opponent.hitPoints -= move.power;
                System.out.println(opponent.name + "s hitpoints were reduced by " + move.power);
                System.out.println(opponent.name + " now has " + opponent.hitPoints + " hitpoints remaining.");
                found = true;
            }
        }
        //QUESTION: Warum wird diese NAchricht zweimal geloggt?
        if (!found) {
            System.out.println("This move does not exist");
        }

        //TO DO: weitere logik - hitpoints vom opponent lösen bei 0 eine Handlung aus

    }

}
