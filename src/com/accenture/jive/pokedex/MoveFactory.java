package com.accenture.jive.pokedex;

public class MoveFactory {

    public Move createMove(String moveName, int movePower) {
        Move move = new Move();
        move.name = moveName;
        move.power = movePower;
        return move;
    }
}
