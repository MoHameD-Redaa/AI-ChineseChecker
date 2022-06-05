package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class BotPlayer extends Player {
    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void updateGame(Game game) {

        // Nothing to do
        if (game == null || game.gameOver()) {
            return;
        }

        Node node = new Node(game.board);

        Node newNode = game.alphaBetaPruning(0, 1, node, true, Game.MIN, Game.MAX);

        game.move(newNode.x, newNode.y, newNode.newX, newNode.newY);
        game.Turn = 1;
    }
}