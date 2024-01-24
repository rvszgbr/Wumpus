package org.example;

import java.io.Serializable;
import java.util.Scanner;

public class Game implements Serializable {
    private final Wall gameBoard;
    private final Hero player;
    private boolean isGameOver;
    private MainMenu mainMenu = new MainMenu();

    public Game(Wall gameBoard, Hero player, MainMenu mainMenu) {
        this.gameBoard = gameBoard;
        this.player = player;
        this.isGameOver = false;
        setMainMenu(mainMenu);
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public void startNewGame(String userName) {
        playGame();
    }

    public void playGame() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isGameOver) {
                gameBoard.printBoard(player.getPosition().getX(), player.getPosition().getY());
                System.out.println("Hos pozicioja: " + player.getPosition().getX() + ", " + player.getPosition().getY());
                System.out.println("Add meg a mozgasi iranyt (FEL, LE, BALRA, JOBBRA), vagy írd be 'LO' a loveshez, vagy 'KILEPES' a kilepeshez, vagy 'MENTES' a menteshez: ");
                String input = scanner.nextLine().toUpperCase();

                if (input.equals("KILEPES")) {
                    System.out.println("Visszateres a fomenube...");
                    exitGame();
                } else if (input.equals("LO")) {
                    player.shootArrow();
                    System.out.println("Lottel egy nyíllal! Rendelkezesre allo nyílak szama: " + player.getArrows());
                } else if (input.equals("MENTES")) {
                    saveGame();
                    System.out.println("A játék sikeresen el lett mentve.");
                }

                Direction direction;
                try {
                    direction = Direction.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Ervenytelen irany!");
                    continue;
                }

                if (isValidMove(direction)) {
                    movePlayer(direction);

                    char currentChar = gameBoard.getCharAtPosition(player.getPosition().getX(), player.getPosition().getY());
                    if (currentChar == 'P') {
                        System.out.println("Godorbe estel! Vege a jateknak.");
                        isGameOver = true;
                    } else if (currentChar == 'U') {
                        System.out.println("Megolt a Wumpus! Vege a jateknak.");
                        isGameOver = true;
                    } else if (currentChar == 'G') {
                        gameBoard.removeGold(player.getPosition().getX(), player.getPosition().getY());
                        System.out.println("Nalad az arany!");
                    }

                    if (player.getPosition().equals(player.getInitialPosition()) && player.getGoldCount() > 0) {
                        System.out.println("A jatek teljesitve! Gratulalunk!");
                        isGameOver = true;
                    }
                } else {
                    System.out.println("Ervenytelen lepes!");
                }
            }
        }

        System.out.println("\n==============================");
        System.out.println("=== Jatek vege. ===");
        System.out.println("==============================\n");
    }

    private boolean isValidMove(Direction direction) {
        Position nextPosition = calculateNextPosition(direction);
        char nextChar = gameBoard.getCharAtPosition(nextPosition.getX(), nextPosition.getY());

        if (nextChar == 'W') {
            System.out.println("Falba utkoztel! Nem lephetsz tovabb.");
            return false;
        }

        return true;
    }

    public Position calculateNextPosition(Direction direction) {
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

    public void saveGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add meg a menteni kivant fajl nevet: ");
        String saveFileName = scanner.nextLine();

        // Menti a játékot az adatbázisba
        GameSave.saveGame(this, saveFileName);
    }

    public void exitGame() {
        System.out.println("\nVisszateres a fomenube...\n");
        try {
            Thread.sleep(1000); // Vár 1 másodpercet
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MainMenu.getCurrentMainMenu().returnToMainMenu();
    }


    public static Game loadGame(String userName, MainMenu mainMenu) {
        return GameSave.loadGame(userName, mainMenu);
    }
}
