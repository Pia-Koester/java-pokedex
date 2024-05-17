package com.accenture.jive.pokedex.pokemon;

import com.accenture.jive.pokedex.Move;

import java.util.ArrayList;

public class Pokemon {

    /*
     * Neue Klasse für Pokemons wird erstellt, das ist der Blueprint für alle zukünftigen com.accenture.jive.pokedex.Pokemon Objekte
     * Hier definiere ich variablen und methoden, die zu dieser Klasse gehören
     * */
    public String name;
    public String type;
    public int hitPoints;
    public int combatPoints;
    //das moveset array bezieht sich wieder auf eine Klasse mit objekt eigenschaften
    //public com.accenture.jive.pokedex.Move[] moveset = new com.accenture.jive.pokedex.Move[2];
    public ArrayList<Move> moveset = new ArrayList<>();

    public void sound() {
        System.out.println(name + "...." + name + "..." + name);
    }
}
