package com.accenture.jive.pokedex.pokemon;

import com.accenture.jive.pokedex.Move;

import java.util.Collections;

public class PokemonFactory {
    public Pokemon createPokemon(String name, int hitPoints, int combatPoints, String type, Move... move1) {
        //... varargs - so viele wie du möchtest davon - es kann nur ein varargs argument geben und es muss das letzte in der Signatur sein
        Pokemon pokemon = new Pokemon();
        pokemon.name = name;
        pokemon.hitPoints = hitPoints;
        pokemon.combatPoints = combatPoints;
        pokemon.type = type;
        //ArrayList ist teil des Collection Frameworks, hier sind dann andere Klassen enthalten
        Collections.addAll(pokemon.moveset, move1);

        return pokemon;
    }
}
