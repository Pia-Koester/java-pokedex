package com.accenture.jive.pokedex.pokemon;

import com.accenture.jive.pokedex.Monster;
import com.accenture.jive.pokedex.Move;

import java.util.ArrayList;

public class Pokemon extends Monster {

    /*
     * Neue Klasse für Pokemons wird erstellt, das ist der Blueprint für alle zukünftigen com.accenture.jive.pokedex.Pokemon Objekte
     * Hier definiere ich variablen und methoden, die zu dieser Klasse gehören
     * */

    public String type;

    //das moveset array bezieht sich wieder auf eine Klasse mit objekt eigenschaften
    //public com.accenture.jive.pokedex.Move[] moveset = new com.accenture.jive.pokedex.Move[2];
    public ArrayList<Move> moveset = new ArrayList<>();

}
