package com.accenture.jive.pokedex;

import com.accenture.jive.pokedex.pokemon.Pokemon;
import com.accenture.jive.pokedex.pokemon.PokemonFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /***
     * Test project to learn java
     * This time with Arraylists instead of regular arrays
     * */


    public void run() {
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
        ArrayList<Pokemon> pokedex = new ArrayList<>();
        //die Liste Pokemon ist jetzt noch leer, und die Pokemon werden eins nacheinander hinzugefügt
        pokedex.add(p1);
        pokedex.add(p2);

        //ein Array von allen vorhandenen Moves, damit ich drüber loopen kann
        ArrayList<Move> allMoves = new ArrayList<>();
        allMoves.add(vineWhip);
        allMoves.add(tackle);
        allMoves.add(fireFang);

        //Importing and initialising commandos for future use
        AddCommando addCommando = new AddCommando();
        ShowCommando showCommando = new ShowCommando();
        FightCommando fightCommando = new FightCommando();

        //Damit ich input von usern bekommen kann brauche ich eine Scanner Objekt
        Scanner scanner = new Scanner(System.in); //in bedeutet dass was reingeschrieben wird
        System.out.println("Welcome to the World of Pokemon!");
        System.out.println("What do you want to do? ");
        System.out.println("\u001B[35m" + "In case you don't know what to do enter 'help'." + "\u001B[0m");
        //While schleife, sodass nur bei dem exit stichwort das Programm beendet wird
        boolean shouldRun = true;
        while (shouldRun) {

            String line = scanner.nextLine();
            System.out.println("Your choice was: " + "\u001B[33m" + line + "\u001B[0m");

            //QUESTION: Würde es sich hier anbieten lieber Switch Case zu nutzen anstelle von if else?
            //ANSWER: Ist einfach geschmackssache - kann ich selber entscheiden
            if ("exit".equalsIgnoreCase(line)) {
                shouldRun = false;
            } else if ("help".equalsIgnoreCase(line) || "".equals(line)) {
                showHelp();
            } else if ("add".equalsIgnoreCase(line)) {
                addCommando.execute(pokemonFactory, scanner, allMoves, pokedex);
            } else if ("show".equalsIgnoreCase(line)) {
                showCommando.execute(pokedex);
            } else if ("fight".equalsIgnoreCase(line)) {
                fightCommando.execute(opponent, pokedex, scanner);
            }

        }
        System.out.println("Closing your Pokedex.");

    }

    //QUESTION: wo packe ich das hin? In die ShowCommando Klasse?
    public void showHelp() {
        System.out.println("Here are all the possible commands: ");
        System.out.println("If you want to exit this programm enter 'exit'.");
        System.out.println("For all Pokemon in your Pokedex enter 'show'");
        System.out.println("If you caught a new Pokemon enter 'add'");
        System.out.println("Maybe you will meet a wild pokemon if you enter 'fight'");
    }


    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
