package org.example;

import java.io.Serializable;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class MainMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    private static MainMenu currentMainMenu;
    private Game game;
    private static Scanner scanner = new Scanner(System.in);

    public MainMenu() {
        currentMainMenu = this;
    }

    public static void main(String[] args) {
        GameSave.createTable();
        runMainMenu();
    }

    public void returnToMainMenu() {
        System.out.println("\nVisszateres a fomenube...\n");
        if (game != null) {
            game.exitGame();
        }
    }

    public static MainMenu getCurrentMainMenu() {
        return currentMainMenu;
    }

    public Game getGame() {
        return game;
    }

    private static void runMainMenu() {
        MainMenu mainMenu = new MainMenu();
        boolean exitProgram = false;

        while (!exitProgram) {
            printMenu();

            System.out.print("Valasztott opcio: ");
            try {
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.println("Add meg a felhasznalo nevedet: ");
                        String userName = scanner.nextLine();
                        startNewGame(userName, mainMenu);
                        break;
                    case "2":
                        System.out.println("\nJatek betoltese...");
                        loadGame(mainMenu);
                        break;
                    case "3":
                        System.out.print("Add meg a menteni kivant fajl nevet: ");
                        String saveFileName = scanner.nextLine();
                        if (mainMenu.getGame() != null) {
                            mainMenu.getGame().saveGame();
                        } else {
                            System.out.println("Hiba: Nincs elindított játék.");
                        }
                        break;
                    case "4":
                        System.out.println("\nHighscore megtekintese...");
                        break;
                    case "5":
                        System.out.println("\nKilepes a programbol. Viszlat!");
                        exitProgram = true;
                        break;
                    default:
                        System.out.println("Ervenytelen valasztas. Kerlek, valassz ujra.");
                        break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Nem található további sor a bemeneten.");
                mainMenu.returnToMainMenu();
            }
        }
    }

    private static void startNewGame(String userName, MainMenu mainMenu) {
        Wall wall = new Wall("C:\\Users\\rivas\\Wumpus\\src\\main\\resources\\palya.txt");
        Hero hero = new Hero(new Position(1, 5), Direction.FEL, 3);
        mainMenu.game = new Game(wall, hero, mainMenu);
        mainMenu.game.startNewGame(userName);
    }

    private static void loadGame(MainMenu mainMenu) {
        System.out.print("Add meg a mentett fajl nevet: ");
        String fileName = scanner.nextLine();
        mainMenu.game = Game.loadGame(fileName, mainMenu);
        if (mainMenu.game != null) {
            mainMenu.game.playGame();
        }
    }

    public static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("===     WUMPUS      ===");
        System.out.println("==============================\n");
        System.out.println("1. Uj játék indítasa");
        System.out.println("2. Jatek betoltese");
        System.out.println("3. Jatek mentese");
        System.out.println("4. Highscore megtekintese");
        System.out.println("5. Kilepes");
    }
}
