package com.accenture.jive.pokedex;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static void showCommando(ArrayList<Pokemon> pokedex) {
        //Jetzt wollen wir über die ArrayList loopen um die Objekte zu loggen
        System.out.println("You have already caught " + pokedex.size() + " Pokemon.");
        for (Pokemon pokemon : pokedex) {
            int pokemonPosition = pokedex.indexOf(pokemon) + 1;
            System.out.println("Pokemon No." + pokemonPosition + ": " + pokemon.name);
            System.out.println("This pokemon knows the move: ");
            if (pokemon.moveset.isEmpty()) {
                System.out.println("This pokemon does not know any moves :(");
            } else {
                for (Move move : pokemon.moveset) {
                    if (move != null) {
                        System.out.println(move.name);
                    }

                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

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

        Pokemon p1 = pokemonFactory.createPokemon("Bisasam", 23, 155, "Grass", vineWhip, tackle, fireFang);
        Pokemon p2 = pokemonFactory.createPokemon("Charmander", 77, 88, "Fire", fireFang);
        Pokemon opponent = pokemonFactory.createPokemon("Slaking", 3882, 217, "Normal", slackOff, scratch);

        //Nachdem die einzelnen com.accenture.jive.pokedex.Pokemon erstellt wurden sollen sie teil einer Arrayliste werden
        ArrayList<Pokemon> pokedex = new ArrayList<>();
        //die Liste com.accenture.jive.pokedex.Pokemon ist jetzt noch leer, und die com.accenture.jive.pokedex.Pokemon werden eins nacheinander hinzugefügt
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
        System.out.println("In case you don't know what to do enter 'help'.");
        //endlosschleife, sodass nur bei dem exit stichwort das Programm beendet wird
        boolean shouldRun = true;
        while (shouldRun) {

            String line = scanner.nextLine();
            System.out.println("Your choice was: " + line);

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
        System.out.println("oh no.... a wild " + opponent.name + " appeared.");
        System.out.println("Who should fight this Pokemon? Select: ");
        for (Pokemon pokemon : pokedex) {
            System.out.println(pokemon.name);
        }
        String selectedPokemon = scanner.nextLine();
        Pokemon fightingPokemon = new Pokemon();
        //Im Pokedex wird gecheckt ob das eingegebene Pokemon existiert

        for (Pokemon pokemon : pokedex) {
            if (selectedPokemon.equals(pokemon.name)) {
                fightingPokemon = pokemon;
                System.out.println("You have selected " + pokemon.name + " to fight against" + opponent.name);
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
        for (Move move : fightingPokemon.moveset) {
            if (inputMove.equalsIgnoreCase(move.name)) {
                opponent.hitPoints -= move.power;
                System.out.println(opponent.name + "s hitpoints were reduced by " + move.power);
                System.out.println(opponent.name + " now has " + opponent.hitPoints + " hitpoints remaining.");
                break;
            } else {
                //QUESTION: wie kann ich hier weiterhin beim input Move bleiben und nicht zum anderen Scanner kommen?
                System.out.println("Select a different move");
            }
        }

    }

    public void showHelp() {
        System.out.println("Here are all the possible commands: ");
        System.out.println("If you want to exit this programm enter 'exit'.");
        System.out.println("For all Pokemon in your Pokedex enter 'show'");
        System.out.println("If you caught a new Pokemon enter 'add'");
    }

    public void addCommando(PokemonFactory pokemonFactory, Scanner scanner, ArrayList<Move> allMoves, ArrayList<Pokemon> pokedex) {
        System.out.println("Which Pokemon did you catch?");
        String pokemonName = scanner.nextLine();
        System.out.println("What type is this Pokemon?");
        String pokemonType = scanner.nextLine();
        System.out.println("Combat Points and Hitpoints are being generated....");
        //TO DO: Random number generation for the points
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
        while (!found) {
            for (Move move : allMoves) {
                if (inputMove.equals(move.name)) {
                    selectedMove = move;
                    found = true;
                } else {
                    System.out.println("This move does not exist");
                }
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

}
