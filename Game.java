package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    public static final int INVALID = -1;
    public static final int EMPTY = 0;
    public static final int HUMAN_PLAYER = 1;
    public static final int BOT_PLAYER = 2;
    public static final int HEIGHT = 17;
    public static final int WIDTH = 25;
    public static int MAX = 10000;
    public static int MIN = -10000;

    public int[][] board, tmpboard;
    public ArrayList<Cell> playerList = new ArrayList<>();
    public ArrayList<Cell> playerStartList = new ArrayList<>();
    public ArrayList<Cell> botList = new ArrayList<>();
    public ArrayList<Cell> botStartList = new ArrayList<>();
    public int Turn;

    public Game() {
        board = new int[HEIGHT][WIDTH];
        buildBoard();
        loadTmpBoard();
        this.Turn = 1; // that mean the player will play first
    }

    public Game(int[][] board) {
        this.board = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        loadTmpBoard();
        this.Turn = 1; // that mean the player will play first
    }

    public void restart() {
        buildBoard();
        loadTmpBoard();
        this.Turn = 1;
    }

    public Game copy() {
        Game g = new Game();
        g.board = this.board;
        g.tmpboard = this.tmpboard;
        g.Turn = this.Turn;
        return g;
    }

    public void buildBoard() {

        // set empty cells to 0
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                board[i][j] = EMPTY;

        // set green cells to 1
        board[0][12] = BOT_PLAYER;
        botList.add(new Cell(0, 12));
        botStartList.add(new Cell(0, 12));

        board[1][11] = BOT_PLAYER;
        botList.add(new Cell(1, 11));
        botStartList.add(new Cell(1, 11));

        board[1][13] = BOT_PLAYER;
        botList.add(new Cell(1, 13));
        botStartList.add(new Cell(1, 13));

        board[2][10] = BOT_PLAYER;
        botList.add(new Cell(2, 10));
        botStartList.add(new Cell(2, 10));

        board[2][12] = BOT_PLAYER;
        botList.add(new Cell(2, 12));
        botStartList.add(new Cell(2, 12));

        board[2][14] = BOT_PLAYER;
        botList.add(new Cell(2, 14));
        botStartList.add(new Cell(2, 14));

        board[3][9] = BOT_PLAYER;
        botList.add(new Cell(3, 9));
        botStartList.add(new Cell(3, 9));

        board[3][11] = BOT_PLAYER;
        botList.add(new Cell(3, 11));
        botStartList.add(new Cell(3, 11));

        board[3][13] = BOT_PLAYER;
        botList.add(new Cell(3, 13));
        botStartList.add(new Cell(3, 13));

        board[3][15] = BOT_PLAYER;
        botList.add(new Cell(3, 15));
        botStartList.add(new Cell(3, 15));


        // set red cells to 2
        board[13][9] = HUMAN_PLAYER;
        playerList.add(new Cell(13, 9));
        playerStartList.add(new Cell(13, 9));

        board[13][11] = HUMAN_PLAYER;
        playerList.add(new Cell(13, 11));
        playerStartList.add(new Cell(13, 11));

        board[13][13] = HUMAN_PLAYER;
        playerList.add(new Cell(13, 13));
        playerStartList.add(new Cell(13, 13));

        board[13][15] = HUMAN_PLAYER;
        playerList.add(new Cell(13, 15));
        playerStartList.add(new Cell(13, 15));

        board[14][10] = HUMAN_PLAYER;
        playerList.add(new Cell(14, 10));
        playerStartList.add(new Cell(14, 10));

        board[14][12] = HUMAN_PLAYER;
        playerList.add(new Cell(14, 12));
        playerStartList.add(new Cell(14, 12));

        board[14][14] = HUMAN_PLAYER;
        playerList.add(new Cell(14, 14));
        playerStartList.add(new Cell(14, 14));

        board[15][11] = HUMAN_PLAYER;
        playerList.add(new Cell(15, 11));
        playerStartList.add(new Cell(15, 11));

        board[15][13] = HUMAN_PLAYER;
        playerList.add(new Cell(15, 13));
        playerStartList.add(new Cell(15, 13));

        board[16][12] = HUMAN_PLAYER;
        playerList.add(new Cell(16, 12));
        playerStartList.add(new Cell(16, 12));

        Collections.reverse(botStartList);
        Collections.reverse(playerStartList);
        Collections.reverse(playerList);

        // first 4 rows
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < WIDTH; j++)
                if (board[i][j] == 0)
                    board[i][j] = INVALID;
        // last 4 rows
        for (int i = 13; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++)
                if (board[i][j] == 0)
                    board[i][j] = INVALID;

        int jump = 0;
        for (int i = 4; i < 13; i++) {
            // row 8
            if (i == 8) {
                for (int j = 0; j < 4; j++)
                    board[i][j] = INVALID;
                for (int j = WIDTH - 1; j > 20; j--)
                    board[i][j] = INVALID;
                jump++;
                for (int j = jump; j < WIDTH; j += 2)
                    board[i][j] = INVALID;
                continue;
            }
            //left side
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == 0)
                    board[i][j] = INVALID;
                else
                    break;
            }
            //right side
            for (int j = WIDTH - 1; j >= 0; j--) {
                if (board[i][j] == 0)
                    board[i][j] = INVALID;
                else
                    break;
            }
            // between numbers
            if (i < 9)
                jump++;
            else
                jump--;
            for (int j = jump; j < WIDTH; j += 2) {
                board[i][j] = INVALID;
            }
        }

        jump = 0;
        for (int i = 4; i < 13; i++) {
            if (i == 8) {
                jump--;
                continue;
            }
            for (int j = jump; j < WIDTH - jump; j += 2) {
                board[i][j] = EMPTY;
            }
            if (i < 8)
                jump++;
            else
                jump--;
        }
    }

    public void loadTmpBoard() {
        this.tmpboard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
    }

    public void loadToBoard() {
        this.board = Arrays.stream(tmpboard).map(int[]::clone).toArray(int[][]::new);
    }

    // adjacent moves
    public boolean moveHorizontalL(int positionX, int positionY) {
        if (board[positionY][positionX - 2] == EMPTY) {
            board[positionY][positionX - 2] = board[positionY][positionX];
            board[positionY][positionX] = EMPTY;
            loadTmpBoard();
            return true;
        }
        return false;
    }

    public boolean moveHorizontalR(int positionX, int positionY) {
        if (board[positionY][positionX + 2] == EMPTY) {
            board[positionY][positionX + 2] = board[positionY][positionX];
            board[positionY][positionX] = EMPTY;
            loadTmpBoard();
            return true;
        }
        return false;
    }

    public boolean moveDiagonalUR(int positionX, int positionY) {
        if (board[positionY - 1][positionX + 1] == EMPTY) {
            board[positionY - 1][positionX + 1] = board[positionY][positionX];
            board[positionY][positionX] = EMPTY;
            loadTmpBoard();
            return true;
        }
        return false;
    }

    public boolean moveDiagonalUL(int positionX, int positionY) {
        if (board[positionY - 1][positionX - 1] == EMPTY) {
            board[positionY - 1][positionX - 1] = board[positionY][positionX];
            board[positionY][positionX] = EMPTY;
            loadTmpBoard();
            return true;
        }
        return false;
    }

    public boolean moveDiagonalDR(int positionX, int positionY) {
        if (board[positionY + 1][positionX + 1] == EMPTY) {
            board[positionY + 1][positionX + 1] = board[positionY][positionX];
            board[positionY][positionX] = EMPTY;
            loadTmpBoard();
            return true;
        }
        return false;
    }

    public boolean moveDiagonalDL(int positionX, int positionY) {
        if (board[positionY + 1][positionX - 1] == EMPTY) {
            board[positionY + 1][positionX - 1] = board[positionY][positionX];
            board[positionY][positionX] = EMPTY;
            loadTmpBoard();
            return true;
        }
        return false;
    }

    // verify move for player
    public boolean verifyAdjMoveForP(int currentX, int currentY, int goalX, int goalY) {
        int value = board[currentY][currentX];
        if (!(value == BOT_PLAYER)) {
            System.out.println("You can't access this marble");
            return false;
        }
        if (currentX == goalX && currentY == goalY) {
            System.out.println("can't play in same place");
            return false;
        }
        // check move left
        if (currentY == goalY && currentX - 2 == goalX) {
            if (board[currentY][currentX - 2] == EMPTY)
                return true;
            return false;
        }

        // check move right
        else if (currentY == goalY && currentX + 2 == goalX) {
            if (board[currentY][currentX + 2] == EMPTY)
                return true;
            return false;
        }

        // check move diagonal up right
        else if (currentY - 1 == goalY && currentX + 1 == goalX) {
            if (board[currentY - 1][currentX + 1] == EMPTY)
                return true;
            return false;
        }

        // check move diagonal up left
        else if (currentY - 1 == goalY && currentX - 1 == goalX) {
            if (board[currentY - 1][currentX - 1] == EMPTY)
                return true;
            return false;
        }

        // check move diagonal down right
        else if (currentY + 1 == goalY && currentX + 1 == goalX) {
            if (board[currentY + 1][currentX + 1] == EMPTY)
                return true;
            return false;
        }

        // check move diagonal down left
        else if (currentY + 1 == goalY && currentY - 1 == goalX) {
            if (board[currentY + 1][currentX - 1] == EMPTY)
                return true;
            return false;
        }

        //try another move
        ArrayList<int[][]> states = new ArrayList<>();
        states.add(board);
        return verifyHopMoveForP(currentX, currentY, goalX, goalY, states);
    }

    // hop moves
    public boolean moveHobHorizontalL(int positionX, int positionY) {
        if (tmpboard[positionY][positionX - 2] != EMPTY && tmpboard[positionY][positionX - 4] == EMPTY) {
            tmpboard[positionY][positionX - 4] = tmpboard[positionY][positionX];
            tmpboard[positionY][positionX] = EMPTY;
            return true;
        }
        return false;
    }

    public boolean moveHobHorizontalR(int positionX, int positionY) {
        if (tmpboard[positionY][positionX + 2] != EMPTY && tmpboard[positionY][positionX + 4] == EMPTY) {
            tmpboard[positionY][positionX + 4] = tmpboard[positionY][positionX];
            tmpboard[positionY][positionX] = EMPTY;
            return true;
        }
        return false;
    }

    public boolean moveHobDiagonalUR(int positionX, int positionY) {
        if (tmpboard[positionY - 1][positionX + 1] != EMPTY && tmpboard[positionY - 2][positionX + 2] == EMPTY) {
            tmpboard[positionY - 2][positionX + 2] = tmpboard[positionY][positionX];
            tmpboard[positionY][positionX] = EMPTY;
            return true;
        }
        return false;
    }

    public boolean moveHobDiagonalUL(int positionX, int positionY) {
        if (tmpboard[positionY - 1][positionX - 1] != EMPTY && tmpboard[positionY - 2][positionX - 2] == EMPTY) {
            tmpboard[positionY - 2][positionX - 2] = tmpboard[positionY][positionX];
            tmpboard[positionY][positionX] = EMPTY;
            return true;
        }
        return false;
    }

    public boolean moveHobDiagonalDR(int positionX, int positionY) {
        if (tmpboard[positionY + 1][positionX + 1] != EMPTY && tmpboard[positionY + 2][positionX + 2] == EMPTY) {
            tmpboard[positionY + 2][positionX + 2] = tmpboard[positionY][positionX];
            tmpboard[positionY][positionX] = EMPTY;
            return true;
        }
        return false;
    }

    public boolean moveHobDiagonalDL(int positionX, int positionY) {
        if (tmpboard[positionY + 1][positionX - 1] == EMPTY && tmpboard[positionY + 2][positionX - 2] == EMPTY) {
            tmpboard[positionY + 2][positionX - 2] = tmpboard[positionY][positionX];
            tmpboard[positionY][positionX] = EMPTY;
            return true;
        }
        return false;
    }

    // check new state contain in states
    public boolean checkState(ArrayList<int[][]> states) {
        int count;
        for (int i = 0; i < states.size(); i++) {
            count = 0;
            for (int j = 0; j < HEIGHT; j++) {
                if (Arrays.equals(states.get(i)[j], tmpboard[j]))
                    count++;
            }
            if (count == HEIGHT)
                return true;
        }
        return false;
    }

    //start from current and recursion call to find goal if found
    public boolean verifyHopMoveForP(int currentX, int currentY, int goalX, int goalY, ArrayList<int[][]> states) {
        if (currentX == goalX && currentY == goalY) {
            return true;
        }
        //left
        if (currentX >= 4) {
            if (tmpboard[currentY][currentX - 2] != EMPTY && tmpboard[currentY][currentX - 4] == EMPTY) {
                moveHobHorizontalL(currentX, currentY);
                if (checkState(states)) {
                    moveHobHorizontalR(currentX - 4, currentY);
                } else {
                    int[][] newBoard = Arrays.stream(tmpboard).map(int[]::clone).toArray(int[][]::new);
                    states.add(newBoard);
                    currentX -= 4;
                    if (verifyHopMoveForP(currentX, currentY, goalX, goalY, states))
                        return true;
                }
            }
        }

        //right
        if (currentX <= WIDTH - 5) {
            if (tmpboard[currentY][currentX + 2] != EMPTY && tmpboard[currentY][currentX + 4] == EMPTY) {
                moveHobHorizontalR(currentX, currentY);
                if (checkState(states)) {
                    moveHobHorizontalL(currentX + 4, currentY);
                } else {
                    int[][] newBoard = Arrays.stream(tmpboard).map(int[]::clone).toArray(int[][]::new);
                    states.add(newBoard);
                    currentX += 4;
                    if (verifyHopMoveForP(currentX, currentY, goalX, goalY, states))
                        return true;
                }
            }
        }

        //up right
        if (currentY >= 2 && currentX <= WIDTH - 3) {
            if (tmpboard[currentY - 1][currentX + 1] != EMPTY && tmpboard[currentY - 2][currentX + 2] == EMPTY) {

                moveHobDiagonalUR(currentX, currentY);
                if (checkState(states)) {
                    moveHobDiagonalDL(currentX + 2, currentY - 2);
                } else {
                    int[][] newBoard = Arrays.stream(tmpboard).map(int[]::clone).toArray(int[][]::new);
                    states.add(newBoard);
                    currentX += 2;
                    currentY -= 2;
                    if (verifyHopMoveForP(currentX, currentY, goalX, goalY, states))
                        return true;
                }
            }
        }

        //up left
        if (currentY >= 2 && currentX >= 2) {
            if (tmpboard[currentY - 1][currentX - 1] != EMPTY && tmpboard[currentY - 2][currentX - 2] == EMPTY) {
                moveHobDiagonalUL(currentX, currentY);
                if (checkState(states)) {
                    moveHobDiagonalDR(currentX - 2, currentY - 2);
                } else {
                    int[][] newBoard = Arrays.stream(tmpboard).map(int[]::clone).toArray(int[][]::new);
                    states.add(newBoard);
                    currentX -= 2;
                    currentY -= 2;
                    if (verifyHopMoveForP(currentX, currentY, goalX, goalY, states))
                        return true;
                }
            }
        }

        //down right
        if (currentY <= HEIGHT - 3 && currentX <= WIDTH - 3) {
            if (tmpboard[currentY + 1][currentX + 1] != EMPTY && tmpboard[currentY + 2][currentX + 2] == EMPTY) {
                moveHobDiagonalDR(currentX, currentY);
                if (checkState(states)) {
                    moveHobDiagonalUL(currentX + 2, currentY + 2);
                } else {
                    int[][] newBoard = Arrays.stream(tmpboard).map(int[]::clone).toArray(int[][]::new);
                    states.add(newBoard);
                    currentX += 2;
                    currentY += 2;
                    if (verifyHopMoveForP(currentX, currentY, goalX, goalY, states))
                        return true;
                }
            }
        }

        //down left
        if (currentY <= HEIGHT - 3 && currentX >= 2) {
            if (tmpboard[currentY + 1][currentX - 1] != EMPTY && tmpboard[currentY + 2][currentX - 2] == EMPTY) {
                moveHobDiagonalDL(currentX, currentY);
                if (checkState(states)) {
                    moveHobDiagonalUR(currentX - 2, currentY + 2);
                } else {
                    int[][] newBoard = Arrays.stream(tmpboard).map(int[]::clone).toArray(int[][]::new);
                    states.add(newBoard);
                    currentX -= 2;
                    currentY += 2;
                    if (verifyHopMoveForP(currentX, currentY, goalX, goalY, states))
                        return true;
                }
            }
        }
        return false;
    }

    // check available adjacent moves for a marble
    public void CheckAdjMove(Cell point) {
        // Horizontal Left
        if (point.x >= 2) {
            if (board[point.y][point.x - 2] == EMPTY && !checkStateByXY(point, point.x - 2, point.y)) {
                point.AvailableCells.add(point.x - 2);
                point.AvailableCells.add(point.y);
            }
        }
        // Horizontal Right
        if (point.x <= WIDTH - 3) {
            if (board[point.y][point.x + 2] == EMPTY && !checkStateByXY(point, point.x + 2, point.y)) {
                point.AvailableCells.add(point.x + 2);
                point.AvailableCells.add(point.y);
            }
        }
        // Up right
        if (point.x <= WIDTH - 2 && point.y >= 1) {
            if (board[point.y - 1][point.x + 1] == EMPTY && !checkStateByXY(point, point.x + 1, point.y - 1)) {
                point.AvailableCells.add(point.x + 1);
                point.AvailableCells.add(point.y - 1);
            }
        }
        // Up left
        if (point.x >= 1 && point.y >= 1) {
            if (board[point.y - 1][point.x - 1] == EMPTY && !checkStateByXY(point, point.x - 1, point.y - 1)) {
                point.AvailableCells.add(point.x - 1);
                point.AvailableCells.add(point.y - 1);
            }
        }
        // Down right
        if (point.x <= WIDTH - 2 && point.y <= HEIGHT - 2) {
            if (board[point.y + 1][point.x + 1] == EMPTY && !checkStateByXY(point, point.x + 1, point.y + 1)) {
                point.AvailableCells.add(point.x + 1);
                point.AvailableCells.add(point.y + 1);
            }
        }
        // Down left
        if (point.x >= 1 && point.y <= HEIGHT - 2) {
            if (board[point.y + 1][point.x - 1] == EMPTY && !checkStateByXY(point, point.x - 1, point.y + 1)) {
                point.AvailableCells.add(point.x - 1);
                point.AvailableCells.add(point.y + 1);
            }
        }
    }

    // check x and y didn't appear before
    public boolean checkStateByXY(Cell point, int x, int y) {
        for (int i = 0; i < point.AvailableCells.size(); i += 2) {
            if (point.AvailableCells.get(i) == x && point.AvailableCells.get(i + 1) == y)
                return true;
        }
        return false;
    }

    // check available hob moves for a marble
    public void CheckNonAdjMove(Cell point, int x, int y) {
        //left
        if (x >= 4) {
            if (tmpboard[y][x - 2] != EMPTY && tmpboard[y][x - 4] == EMPTY) {
                if (!checkStateByXY(point, x - 4, y)) {
                    point.AvailableCells.add(x - 4);
                    point.AvailableCells.add(y);
                    CheckNonAdjMove(point, x - 4, y);
                }
            }
        }

        //right
        if (x <= WIDTH - 5) {
            if (tmpboard[y][x + 2] != EMPTY && tmpboard[y][x + 4] == EMPTY) {
                if (!checkStateByXY(point, x + 4, y)) {
                    point.AvailableCells.add(x + 4);
                    point.AvailableCells.add(y);
                    CheckNonAdjMove(point, x + 4, y);
                }
            }
        }

        //up right
        if (y >= 2 && x <= WIDTH - 3) {
            if (tmpboard[y - 1][x + 1] != EMPTY && tmpboard[y - 2][x + 2] == EMPTY) {
                if (!checkStateByXY(point, x + 2, y - 2)) {
                    point.AvailableCells.add(x + 2);
                    point.AvailableCells.add(y - 2);
                    CheckNonAdjMove(point, x + 2, y - 2);
                }
            }
        }

        //up left
        if (y >= 2 && x >= 2) {
            if (tmpboard[y - 1][x - 1] != EMPTY && tmpboard[y - 2][x - 2] == EMPTY) {
                if (!checkStateByXY(point, x - 2, y - 2)) {
                    point.AvailableCells.add(x - 2);
                    point.AvailableCells.add(y - 2);
                    CheckNonAdjMove(point, x - 2, y - 2);
                }
            }
        }

        //down right
        if (y <= HEIGHT - 3 && x <= WIDTH - 3) {
            if (tmpboard[y + 1][x + 1] != EMPTY && tmpboard[y + 2][x + 2] == EMPTY) {
                if (!checkStateByXY(point, x + 2, y + 2)) {
                    point.AvailableCells.add(x + 2);
                    point.AvailableCells.add(y + 2);
                    CheckNonAdjMove(point, x + 2, y + 2);
                }
            }
        }

        //down left
        if (y <= HEIGHT - 3 && x >= 2) {
            if (tmpboard[y + 1][x - 1] != EMPTY && tmpboard[y + 2][x - 2] == EMPTY) {
                if (!checkStateByXY(point, x - 2, y + 2)) {
                    point.AvailableCells.add(x - 2);
                    point.AvailableCells.add(y + 2);
                    CheckNonAdjMove(point, x - 2, y + 2);
                }
            }
        }
    }

    // get all available cells for player and bot turn 0 for bot and turn 1 for player
    public ArrayList<Cell> getAvailableCells(int turn) {
        ArrayList<Cell> List = new ArrayList<>();
        int value;
        if (turn == 1)
            value = HUMAN_PLAYER;
        else
            value = BOT_PLAYER;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == value) {
                    List.add(new Cell(j, i));
                    CheckNonAdjMove(List.get(List.size() - 1), j, i);
                    CheckAdjMove(List.get(List.size() - 1));
                }
            }
        }
        return List;
    }

    // direct move
    public void move(int x, int y, int newx, int newy) {
        board[newy][newx] = board[y][x];
        board[y][x] = EMPTY;
        loadTmpBoard();
    }

    //check if player is win
    public boolean playerWon() {
        if (board[0][12] == HUMAN_PLAYER && board[1][11] == HUMAN_PLAYER && board[1][13] == HUMAN_PLAYER &&
                board[2][10] == HUMAN_PLAYER && board[2][12] == HUMAN_PLAYER && board[2][14] == HUMAN_PLAYER &&
                board[3][9] == HUMAN_PLAYER && board[3][11] == HUMAN_PLAYER && board[3][13] == HUMAN_PLAYER &&
                board[3][15] == HUMAN_PLAYER)
            return true;
        return false;
    }

    //check if bot is win
    public boolean botWon() {
        if (board[13][9] == BOT_PLAYER && board[13][11] == BOT_PLAYER && board[13][13] == BOT_PLAYER &&
                board[13][15] == BOT_PLAYER && board[14][10] == BOT_PLAYER && board[14][12] == BOT_PLAYER &&
                board[14][14] == BOT_PLAYER && board[15][11] == BOT_PLAYER && board[15][13] == BOT_PLAYER &&
                board[16][12] == BOT_PLAYER)
            return true;
        return false;
    }

    //check if game is over
    public boolean gameOver() {
        if (playerWon() || botWon()) {
            return true;
        } else
            return false;
    }

    //print
    public void printBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // get heuristic value of current board
    // ---------- heuristic function ------------

    public int getHeuristic(int[][] tBoard)
    {
        Game game = new Game(tBoard);
        int minVal = 100000;

        for (Cell p : game.getAvailableCells(0)) {
            int x, y, restmp;

            for(Cell goalP : playerStartList) {
                restmp = Math.abs(p.x - goalP.y) + Math.abs(p.y - goalP.x);
                if (restmp < minVal)
                {
                    minVal = restmp;
                }
            }
        }
        return minVal;
    }

    // generate all possible boards of current board by moving each marble to one of each possible move in AvailableCells
    public ArrayList<Node> generateNextState(int[][] board, int turn) {
        ArrayList<Node> resultList = new ArrayList<>();
        Game tmpGame = new Game(board);
        ArrayList<Cell> available = tmpGame.getAvailableCells(turn);

        for (Cell p : available) {
            int x, y;

            for (int i = 0; i < p.AvailableCells.size(); i += 2) {

                x = p.AvailableCells.get(i);	// because p.AvailableCells return [y, x, y, x, ....]
                y = p.AvailableCells.get(i + 1);

                int[][] tmpBoard = Arrays.stream(tmpGame.board).map(int[]::clone).toArray(int[][]::new);

                tmpBoard[p.y][p.x] = EMPTY;
                tmpBoard[y][x] = turn==0?BOT_PLAYER:HUMAN_PLAYER; // because p.AvailableCells return [y, x, y, x, ....]}

                Node tmpNode = new Node();
                tmpNode.board = Arrays.stream(tmpBoard).map(int[]::clone).toArray(int[][]::new);

                tmpNode.x = p.x;
                tmpNode.y = p.y;
                tmpNode.newX = x;
                tmpNode.newY = y;

                resultList.add(tmpNode);
            }
        }
        return resultList;
    }

    // ---------- Alpha-Beta -------------

    public Node alphaBetaPruning(int depth, int maxDepth, Node currentNode, Boolean maximizingPlayer, int alpha, int beta) {

        // generate new boards for the next level of the tree
        if (depth == maxDepth)
        {
            int hueVal = getHeuristic(currentNode.board);
            currentNode.heuristicVal = hueVal;
            return currentNode;
        }

        ArrayList<Node> subStates = new ArrayList<>();
        if(depth%2==0) {
            subStates = generateNextState(currentNode.board,0);
        }
        else {
            subStates = generateNextState(currentNode.board,1);
        }

        // when reach required depth calc the heuristic value of this board

        Node bestNode = new Node();
        if (!(maximizingPlayer)) {
            bestNode.heuristicVal = MIN;

            // call alphaBeta for each subState
            for (int i = 0; i < subStates.size(); i++) {

                Node nextNode = alphaBetaPruning(depth + 1, maxDepth, subStates.get(i), false, alpha, beta);

                if (bestNode.heuristicVal < nextNode.heuristicVal)
                {
                    bestNode.heuristicVal = nextNode.heuristicVal;

                    bestNode.board = Arrays.stream(subStates.get(i).board).map(int[]::clone).toArray(int[][]::new);
                    bestNode.newX = subStates.get(i).newX;
                    bestNode.newY = subStates.get(i).newY;
                    bestNode.x = subStates.get(i).x;
                    bestNode.y = subStates.get(i).y;
                }
                //best = Math.max(best, nextNode.heuristicVal);
                alpha = Math.max(alpha, bestNode.heuristicVal);

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }

        } else {
            //int best = MAX;
            bestNode.heuristicVal = MAX;
            // Recur for left and
            // right children

            for (int i = 0; i < subStates.size(); i++) {

                Node nextNode = alphaBetaPruning(depth + 1, maxDepth, subStates.get(i), true, alpha, beta);

                // get max best node
                if(bestNode.heuristicVal > nextNode.heuristicVal)
                {
                    bestNode.heuristicVal = nextNode.heuristicVal;

                    bestNode.board = Arrays.stream(subStates.get(i).board).map(int[]::clone).toArray(int[][]::new);

                    bestNode.newX = subStates.get(i).newX;
                    bestNode.newY = subStates.get(i).newY;
                    bestNode.x = subStates.get(i).x;
                    bestNode.y = subStates.get(i).y;
                }
                beta = Math.min(beta, bestNode.heuristicVal);
                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
        }
        return bestNode;
    }
}