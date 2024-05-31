package com.accenture.jive.pokedex.commandos;

public class HelpCommando implements Commando {

    //QUESTION: wo packe ich das hin? In die ShowCommando Klasse? - oder hier?
    public boolean execute() {
        System.out.println("Here are all the possible commands: ");
        System.out.println("If you want to exit this programm enter 'exit'.");
        System.out.println("For all Pokemon in your Pokedex enter 'show'");
        System.out.println("If you caught a new Pokemon enter 'add'");
        System.out.println("Maybe you will meet a wild pokemon if you enter 'fight'");
        return true;
    }
}
