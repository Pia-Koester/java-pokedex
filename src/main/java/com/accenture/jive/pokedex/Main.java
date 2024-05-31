package com.accenture.jive.pokedex;

import com.accenture.jive.pokedex.commandos.*;
import com.accenture.jive.pokedex.pokemon.Pokemon;
import com.accenture.jive.pokedex.pokemon.PokemonFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /***
     * Test project to learn java
     * This time with Arraylists instead of regular arrays
     * */


    public void run() {

        ArrayList<Monster> catalogue = new ArrayList<>();
        ArrayList<Move> allMoves = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); //in bedeutet dass was reingeschrieben wird
        PokemonFactory pokemonFactory = new PokemonFactory();
        //Creating the moves to fill the move sets
        MoveFactory moveFactory = new MoveFactory();
        Move vineWhip = moveFactory.createMove("Vine Whip", 45);
        Move tackle = moveFactory.createMove("Tackle", 33);
        Move fireFang = moveFactory.createMove("Fire Fang", 74);
        Move slackOff = moveFactory.createMove("Slack Off", 75);
        Move scratch = moveFactory.createMove("Scratch", 40);

        //Creating the initial pokemon which are always part of the pokedex
        Pokemon p1 = pokemonFactory.createPokemon("Bisasam", 23, 155, "Grass", vineWhip, tackle, fireFang);
        Pokemon p2 = pokemonFactory.createPokemon("Charmander", 77, 88, "Fire", fireFang);
        Pokemon opponent = pokemonFactory.createPokemon("Slaking", 3882, 217, "Normal", slackOff, scratch);

        //Nachdem die einzelnen .Pokemon erstellt wurden sllen sie teil einer Arrayliste werden
        //die Liste Pokemon ist jetzt noch leer, und die Pokemon werden eins nacheinander hinzugefügt
        catalogue.add(p1);
        catalogue.add(p2);

        //ein Array von allen vorhandenen Moves, damit ich drüber loopen kann
        allMoves.add(vineWhip);
        allMoves.add(tackle);
        allMoves.add(fireFang);

        //Importing and initialising commandos for future use
        Commando addCommando = new AddCommando(scanner, pokemonFactory, catalogue, allMoves);
        Commando showCommando = new ShowCommando(catalogue);
        Commando fightCommando = new FightCommando(scanner, catalogue, opponent);
        Commando helpCommando = new HelpCommando();

        List<Commando> commandos = new ArrayList<>();
        commandos.add(addCommando);
        commandos.add(showCommando);
        commandos.add(fightCommando);
        commandos.add(helpCommando);

        //Damit ich input von usern bekommen kann brauche ich eine Scanner Objekt
        System.out.println("Welcome to the World of Pokemon!");
        System.out.println("What do you want to do? ");
        //While schleife, sodass nur bei dem exit stichwort das Programm beendet wird
        boolean shouldRun = true;
        while (shouldRun) {
            System.out.println("\u001B[35m" + "In case you don't know what to do enter 'help'." + "\u001B[0m");

            String userInput = scanner.nextLine();
            System.out.println("Your choice was: " + "\u001B[33m" + userInput + "\u001B[0m");
            for (Commando commando : commandos) {
                if (commando.shouldExecute(userInput)) {
                    commando.execute();
                }
            }

        }
        System.out.println("Closing your Pokedex.");

    }


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
