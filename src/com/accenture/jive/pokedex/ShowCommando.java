package com.accenture.jive.pokedex;

import com.accenture.jive.pokedex.pokemon.Pokemon;

import java.util.ArrayList;

public class ShowCommando {

    public ArrayList<Pokemon> pokedex;

    public ShowCommando(ArrayList<Pokemon> pokedex) {

        this.pokedex = pokedex;
    }

    public void execute() {
        //Ziel von showCommando ist es alle Pokemon im Pokedex inklusive aller Moves zu loggen
        //Anzeigen wie viele Pokemon insgesamt in der Liste sind:
        System.out.println("You have already caught " + pokedex.size() + " Pokemon.");
        //loop um die einzelnen Namen zu loggen
        for (Pokemon pokemon : pokedex) {
            int pokemonPosition = pokedex.indexOf(pokemon) + 1;
            System.out.println("Pokemon No." + pokemonPosition + ": " + pokemon.name);
            System.out.println("This pokemon knows the move: ");
            //loop über alle Moves, damit diese abgebildet werden können. Falls die ArrayList leer ist kommt eine andere Nachricht
            if (pokemon.moveset.isEmpty()) {
                System.out.println("This pokemon does not know any moves :(");
            } else {
                for (Move move : pokemon.moveset) {
                    //QUESTION: braucht es diese if Bedingung jetzt eigentlich noch? Bin ja von array zu ArrayList gewechselt
                    //ANSWER: null könnte auch als Wert in der ArrayList sein, dann ist sie nicht mehr Empty
                    if (move != null) {
                        System.out.println(move.name);
                    }

                }
            }
        }
    }
}
