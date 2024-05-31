package com.accenture.jive.pokedex.commandos;

public interface Commando {
    public boolean execute();

    public boolean shouldExecute(String userInput);
}
