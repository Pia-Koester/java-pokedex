package com.accenture.jive.pokedex;

import com.accenture.jive.pokedex.pokemon.Pokemon;
import com.accenture.jive.pokedex.pokemon.PokemonFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AddCommando {

    public Scanner scanner;
    public PokemonFactory pokemonFactory;
    public ArrayList<Pokemon> pokedex;
    public ArrayList<Move> allMoves;

    public AddCommando(Scanner scanner, PokemonFactory pokemonFactory, ArrayList<Pokemon> pokedex, ArrayList<Move> allMoves) {
        this.scanner = scanner;

        this.pokemonFactory = pokemonFactory;
        this.pokedex = pokedex;
        this.allMoves = allMoves;
    }

    public void execute() {
        System.out.println("Which Pokemon did you catch?");
        String pokemonName = scanner.nextLine();
        System.out.println("What type is this Pokemon?");
        String pokemonType = scanner.nextLine();
        System.out.println("Combat Points and Hitpoints are being generated....");
        //TO DO: Random number generation for the points
        //QUESTION: kann ich diesen part ausgliedern in eine andere Methode? - Bräuchte ich dafür wieder eine Klasse und dann ein Objekt?
        Random randNum = new Random();
        int upperbound = 500;
        int pokemonCP = randNum.nextInt(upperbound);
        int pokemonHP = randNum.nextInt(upperbound);
        //Create a new pokemon with the provided name and the generated points
        Pokemon newPokemon = pokemonFactory.createPokemon(pokemonName, pokemonHP, pokemonCP, pokemonType);
        System.out.println("You caught " + newPokemon.name + " it has " + newPokemon.hitPoints + " Hitpoints and " + newPokemon.combatPoints + " Combatpoints");
        //TO DO: List of all Moves (arraylist) show and select
        System.out.println("Select which move this pokemon should be able to do");
        for (Move move : allMoves) {
            System.out.println(move.name);
        }
        String inputMove = scanner.nextLine();
        //QUESTION: Wenn der input ein String ist und ich dann innerhalb das arrays nach objects suche, how??
        // allMoves.contains(inputMove);
        boolean found = false;
        Move selectedMove = new Move();
        for (Move move : allMoves) {
            if (inputMove.equals(move.name)) {
                selectedMove = move;
                found = true;
            }
        }
        if (!found) {
            System.out.println("This Move does not exist!");
        }
        newPokemon.moveset.add(selectedMove);

        pokedex.add(newPokemon);
        System.out.println(newPokemon.name + " has been added. It knows the moves");

        if (newPokemon.moveset.isEmpty()) {
            System.out.println("This pokemon does not know any moves :(");
        } else {
            for (Move move : newPokemon.moveset) {
                if (move != null) {
                    System.out.println(move.name);
                }
            }
        }
    }


}
