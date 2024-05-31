package com.accenture.jive.pokedex.commandos;

import com.accenture.jive.pokedex.Move;
import com.accenture.jive.pokedex.pokemon.Pokemon;

import java.util.ArrayList;

public class ShowCommando implements Commando {

    public ArrayList<com.accenture.jive.pokedex.Monster> catalogue;

    public ShowCommando(ArrayList<com.accenture.jive.pokedex.Monster> catalogue) {

        this.catalogue = catalogue;
    }

    public boolean execute() {
        //Ziel von showCommando ist es alle Pokemon im Pokedex inklusive aller Moves zu loggen
        //Anzeigen wie viele Pokemon insgesamt in der Liste sind:
        System.out.println("You have already caught " + catalogue.size() + " Pokemon.");
        //loop um die einzelnen Namen zu loggen
        for (com.accenture.jive.pokedex.Monster monster : catalogue) {
            int pokemonPosition = catalogue.indexOf(monster) + 1;
            System.out.println("Pokemon No." + pokemonPosition + ": " + monster.name);
            System.out.println("This pokemon knows the move: ");
            //loop über alle Moves, damit diese abgebildet werden können. Falls die ArrayList leer ist kommt eine andere Nachricht

//Instance of vorher checken und dann erst umwandeln - Why? - dachte instance of funktioniert anders
            if (monster instanceof Pokemon) { //QUESTION?? warum steht hier das ist always true?
                Pokemon pokemon = (Pokemon) monster;
                if (pokemon.moveset.isEmpty()) {
                    System.out.println("This pokemon does not know any moves :(");
                } else {
                    for (Move move : pokemon.moveset) {
                        //Braucht es diese if Bedingung jetzt eigentlich noch? Bin ja von array zu ArrayList gewechselt
                        //ANSWER: null könnte auch als Wert in der ArrayList sein, dann ist sie nicht mehr Empty
                        if (move != null) {
                            System.out.println(move.name);
                        }

                    }
                }
            } else {
                System.out.println("This monster is not a pokemon and therefore knows no moves");
            }
        }
        return true;
    }
}
