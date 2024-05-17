package com.accenture.jive.pokedex;

import com.accenture.jive.pokedex.pokemon.Pokemon;
import com.accenture.jive.pokedex.pokemon.PokemonFactory;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    /***
     * Test project to learn java
     * This time with Arraylists instead of regular arrays
     * */

    public void showCommando(ArrayList<Pokemon> pokedex) {
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
                    if (move != null) {
                        System.out.println(move.name);
                    }

                }
            }
        }
    }


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
            if ("exit".equalsIgnoreCase(line)) {
                shouldRun = false;
            } else if ("help".equalsIgnoreCase(line) || "".equals(line)) {
                showHelp();
            } else if ("add".equalsIgnoreCase(line)) {
                addCommando(pokemonFactory, scanner, allMoves, pokedex);
            } else if ("show".equalsIgnoreCase(line)) {
                showCommando(pokedex);
            } else if ("fight".equalsIgnoreCase(line)) {
                fightCommando(opponent, pokedex, scanner);
            }

        }
        System.out.println("Closing your Pokedex.");

    }

    public void fightCommando(Pokemon opponent, ArrayList<Pokemon> pokedex, Scanner scanner) {
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

    public void showHelp() {
        System.out.println("Here are all the possible commands: ");
        System.out.println("If you want to exit this programm enter 'exit'.");
        System.out.println("For all Pokemon in your Pokedex enter 'show'");
        System.out.println("If you caught a new Pokemon enter 'add'");
        System.out.println("Maybe you will meet a wild pokemon if you enter 'fight'");
    }

    public void addCommando(PokemonFactory pokemonFactory, Scanner scanner, ArrayList<Move> allMoves, ArrayList<Pokemon> pokedex) {
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

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
