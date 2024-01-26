package org.example;

import java.io.*;
import java.sql.*;

public class GameSave {
    private static final String DATABASE_URL = "jdbc:sqlite:wumpus.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS saved_games (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_name TEXT NOT NULL, " +
                    "game_data BLOB NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveGame(Game game, String userName) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO saved_games (user_name, game_data) VALUES (?, ?)")) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(game);
            }

            byte[] serializedData = baos.toByteArray();

            statement.setString(1, userName);
            statement.setBytes(2, serializedData);

            statement.executeUpdate();

            System.out.println("A játék sikeresen el lett mentve az adatbázisba.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Hiba történt a mentés során.");
        }
    }


    public static Game loadGame(String userName, MainMenu mainMenu) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT game_data FROM saved_games WHERE user_name = ? ORDER BY id DESC LIMIT 1")) {

            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                byte[] data = resultSet.getBytes("game_data");

                try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
                     ObjectInputStream ois = new ObjectInputStream(bais)) {

                    Game game = (Game) ois.readObject();
                    game.setMainMenu(mainMenu);
                    System.out.println("A játék sikeresen betöltve az adatbázisból.");
                    return game;
                }
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Nem sikerült betölteni a játékot az adatbázisból.");
        return null;
    }
}
