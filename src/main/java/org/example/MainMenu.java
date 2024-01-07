package org.example;

import java.util.Scanner;

public class MainMenu {
    private static Game game; // A Game változót itt deklaráljuk

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            System.out.print("Választott opció: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Add meg a felhasználó nevedet: ");
                    String userName = scanner.nextLine();
                    startNewGame(userName);
                    break;
                case "2":
                    System.out.println("\nJáték betöltése...");
                    loadGame(scanner);
                    break;
                case "3":
                    System.out.print("Add meg a menteni kívánt fájl nevét: ");
                    String saveFileName = scanner.nextLine();
                    game.saveGame(saveFileName); // Itt már a game példányon keresztül hívjuk a saveGame metódust
                    break;
                case "4":
                    System.out.println("\nHighscore megtekintése...");
                    break;
                case "5":
                    System.out.println("\nKilépés a programból. Viszlát!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Érvénytelen választás. Kérlek, válassz újra.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("===     WUMPUS      ===");
        System.out.println("==============================\n");
        System.out.println("1. Új játék indítása");
        System.out.println("2. Játék betöltése");
        System.out.println("3. Játék mentése");
        System.out.println("4. Highscore megtekintése");
        System.out.println("5. Kilépés");
    }

    private static void startNewGame(String userName) {
        Wall wall = new Wall("C:\\Users\\rivas\\Wumpus\\src\\main\\resources\\palya.txt");
        Hero hero = new Hero(new Position(1, 5), Direction.FEL, 3);
        game = new Game(wall, hero); // Itt inicializáljuk a game változót
        game.startNewGame(userName);
    }

    private static void loadGame(Scanner scanner) {
        System.out.print("Add meg a mentett fájl nevét: ");
        String fileName = scanner.nextLine();
        game = Game.loadGame(fileName); // Itt is inicializáljuk a game változót
        if (game != null) {
            game.playGame();
        }
    }
}
