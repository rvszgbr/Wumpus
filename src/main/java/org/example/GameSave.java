package org.example;

import java.io.*;
import java.util.Scanner;

public class GameSave {

    public static void saveGame(Game game, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(game);
            System.out.println("Játék sikeresen elmentve: " + fileName);
        } catch (IOException e) {
            System.out.println("Hiba történt a játék mentésekor: " + e.getMessage());
        }
    }

    public static Game loadGame(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Game game = (Game) inputStream.readObject();
            System.out.println("Játék sikeresen betöltve: " + fileName);
            return game;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hiba történt a játék betöltésekor: " + e.getMessage());
            return null;
        }
    }
}
