package com.company;

class Node {
    int x, y;
    int newX, newY;
    int heuristicVal;
    int [][] board;

    public Node()
    {}
    public Node(int [][] board)
    {
        this.board = new int [Game.HEIGHT][];
        for (int j = 0; j < Game.HEIGHT; j++) {
            this.board[j] = board[j].clone();
        }
    }
}