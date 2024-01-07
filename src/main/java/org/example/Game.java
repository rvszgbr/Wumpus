package org.example;

import java.util.Scanner;
public class Game {
    private final Wall gameBoard;
    private final Hero player;
    private boolean isGameOver;

    public Game(Wall gameBoard, Hero player) {
        this.gameBoard = gameBoard;
        this.player = player;
        this.isGameOver = false;
    }

    public void startNewGame(String userName) {
        playGame();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        while (!isGameOver) {
            gameBoard.printBoard(player.getPosition().getX(), player.getPosition().getY());
            System.out.println("Hős pozíciója: " + player.getPosition().getX() + ", " + player.getPosition().getY());
            System.out.println("Add meg a mozgási irányt (FEL, LE, BALRA, JOBBRA), vagy írd be 'LŐ' a lövéshez, vagy 'KILÉPÉS' a kilépéshez: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("KILÉPÉS")) {
                System.out.println("Visszatérés a főmenübe...");
                return;
            } else if (input.equals("LŐ")) {
                player.shootArrow(); // Játékos lövésének végrehajtása
                System.out.println("Lőttél egy nyíllal! Rendelkezésre álló nyílak száma: " + player.getArrows());
            }

            Direction direction;
            try {
                direction = Direction.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Érvénytelen irány!");
                continue;
            }

            if (isValidMove(direction)) {
                movePlayer(direction);

                char currentChar = gameBoard.getCharAtPosition(player.getPosition().getX(), player.getPosition().getY());
                if (currentChar == 'P') {
                    System.out.println("Ködörbe estél! Vége a játéknak.");
                    isGameOver = true;
                } else if (currentChar == 'U') {
                    System.out.println("Megölt a Wumpus! Vége a játéknak.");
                    isGameOver = true;
                } else if (currentChar == 'G') {
                    gameBoard.removeGold(player.getPosition().getX(), player.getPosition().getY());
                    System.out.println("Nálad az arany!");
                }

                // Ellenőrizzük, hogy a játékos visszatért-e a kezdeti pozíciójához arannyal
                if (player.getPosition().equals(player.getInitialPosition()) && player.getGoldCount() > 0) {
                    System.out.println("A játék teljesítve! Gratulálunk!");
                    isGameOver = true;
                }

            } else {
                System.out.println("Érvénytelen lépés!");
            }
        }

        System.out.println("\n==============================");
        System.out.println("=== Játék vége. ===");
        System.out.println("==============================\n");
        scanner.close();
    }

    private boolean isValidMove(Direction direction) {
        Position nextPosition = calculateNextPosition(direction);
        char nextChar = gameBoard.getCharAtPosition(nextPosition.getX(), nextPosition.getY());

        if (nextChar == 'W') {
            System.out.println("Falba ütköztél! Nem léphetsz tovább.");
            return false;
        }

        return true;
    }

    private Position calculateNextPosition(Direction direction) {
        int x = player.getPosition().getX();
        int y = player.getPosition().getY();

        switch (direction) {
            case FEL:
                y--;
                break;
            case LE:
                y++;
                break;
            case BALRA:
                x--;
                break;
            case JOBBRA:
                x++;
                break;
        }

        return new Position(x, y);
    }

    private void movePlayer(Direction direction) {
        Position nextPosition = calculateNextPosition(direction);
        player.setPosition(nextPosition);
    }
    public void saveGame(String fileName) {
        GameSave.saveGame(this, fileName);
    }

    public static Game loadGame(String fileName) {
        return GameSave.loadGame(fileName);
    }
}
