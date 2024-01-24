package org.example;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Wall implements Serializable {

    private char[][] board;

    // Konstruktorban betöltjük a pályát
    public Wall(String filePath) {
        loadGameBoardFromFile(filePath);
    }

    // Pályaszerkezet betöltése fájlból
    private void loadGameBoardFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        board = new char[lines.size()][lines.get(0).length()];

        for (int i = 0; i < lines.size(); i++) {
            board[i] = lines.get(i).toCharArray();
        }
    }

    // Pálya kiírása a konzolra
    public void printBoard(int playerX, int playerY) {
        if (board == null) {
            System.out.println("A palyat még nem toltotted be.");
            return;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Ha az aktuális pozíció egyezik a játékos pozíciójával, írjuk ki egy "H"-t
                if (i == playerY && j == playerX) {
                    System.out.print("H");
                } else {
                    System.out.print(board[i][j]);
                }
            }

            System.out.println(); // Új sor létrehozása a konzolon
        }
    }
    // Visszaadja a karaktert a megadott pozíción
    public char getCharAtPosition(int x, int y) {
        if (x < 0 || y < 0 || x >= board[0].length || y >= board.length) {
            // Ha az indexek kívül esnek a pálya határain, visszaadunk egy üres karaktert
            return ' ';
        }
        return board[y][x];
    }

    // Eltávolítja az "G" karaktert a megadott pozícióból
    public void removeGold(int x, int y) {
        if (x >= 0 && y >= 0 && x < board[0].length && y < board.length && board[y][x] == 'G') {
            board[y][x] = '_'; // Beállítjuk a pozíciót szóköz karakterre
        }
    }
}