package com.accenture.jive.pokedex;


import com.accenture.jive.pokedex.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class FightCommando {

    public Scanner scanner;
    public ArrayList<Monster> catalogue;
    //QUESTION: sollte ich diesen Opponent vielleicht erst hier createn und deshalb eher die Pokemon Factory sowie die Movefactory im constuctor haben?
    public Monster opponent;

    public FightCommando(Scanner scanner, ArrayList<Monster> catalogue, Pokemon opponent
    ) {

        this.scanner = scanner;
        this.catalogue = catalogue;
        this.opponent = opponent;
    }

    public void execute() {
        System.out.println("\u001B[31m" + "oh no.... a wild " + opponent.name + " appeared." + "\u001B[0m");
        System.out.println("Who should fight this Pokemon? Select: ");
        for (Monster monster : catalogue) {
            System.out.println(monster.name);
        }
        String selectedPokemon = scanner.nextLine();
        Monster selectedMonster = new Monster();
        //Im Pokedex wird gecheckt ob das eingegebene Pokemon existiert
        //QUESTION: ich hätte hier auch gerne die Exit Bedingung ohne sie nochmal von vorne schreiben zu müssen

//TO DO: Double check dass das correct verläuft
        for (Monster monster : catalogue) {
            if (selectedPokemon.equals(monster.name)) {
                selectedMonster = monster;
                System.out.println("You have selected " + monster.name + " to fight against " + opponent.name);
                break;
            } else {
                System.out.println("This pokemon is not part of your pokedex.");
            }
        }

        System.out.println("Select the attack from " + selectedMonster.name + "s moveset.");
        //QUESTION: Ich zeige mehrfach alle moves aus dem moveset. Wie kann ich das refactoren, dass es nur einmal geschrieben ist?
        //Mal bezieht es sich auf ein spezifisches Pokemon und mal auf alle moves ...
        //QUESTION: auch hier das Problem - movesets haben nur pokemon und nicht alle Monster
        Pokemon fightingPokemon = (Pokemon) selectedMonster;
        if (fightingPokemon instanceof Pokemon) {
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
        }

        //TO DO: weitere logik - hitpoints vom opponent lösen bei 0 eine Handlung aus

    }

}
