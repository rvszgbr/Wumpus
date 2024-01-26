package org.example;

import java.io.Serializable;

public class Hero implements Serializable {
    private Position position;
    private Direction direction;
    private int arrows; // Játékos nyilainak száma
    private Gold gold;  // Arany objektum
    private Position initialPosition; // Kezdő pozíció tárolása

    public Hero(Position position) {
        // Ellenőrizd, hogy a position ne legyen null
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        this.position = position;
        this.direction = Direction.FEL; // Kezdetben FEL irányba néz
        this.arrows = 3; // Példa: Kezdetben 3 nyilunk van
        this.gold = new Gold();  // Új Gold objektum létrehozása kezdetben
        this.initialPosition = new Position(1, 5);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getInitialPosition() {
        return initialPosition;  // Visszaadja a kezdeti pozíciót
    }

    public void move() {
        if (position == null) {
            throw new IllegalStateException("Position cannot be null");
        }

        switch (direction) {
            case FEL:
                position.setX(position.getX() - 1);
                break;
            case LE:
                position.setX(position.getX() + 1);
                break;
            case BALRA:
                position.setY(position.getY() - 1);
                break;
            case JOBBRA:
                position.setY(position.getY() + 1);
                break;
            default:
                System.out.println("Ervenytelen irany!");
                break;
        }

        // Játék teljesítésének ellenőrzése a lépés után
        checkGameCompletion();
    }

    private void checkGameCompletion() {
        if (position == null || initialPosition == null) {
            throw new IllegalStateException("Position and initialPosition cannot be null");
        }

        System.out.println("Pozicio: " + position);  // Debug információ
        System.out.println("Kezdo pozicio: " + initialPosition);  // Debug információ
        System.out.println("Arany gyujtve: " + gold.isCollected());  // Debug információ
    }

    public void turn(Direction direction) {
        this.direction = direction;
    }

    public void collectGold() {
        gold.collect();  // Arany gyűjtése
    }

    // Getter a goldCount-hoz
    public int getGoldCount() {
        return gold.isCollected() ? 1 : 0;  // Visszaadja az arany darabszámát (1, ha gyűjtve van, egyébként 0)
    }

    public int getArrows() {
        return arrows;
    }

    public void shootArrow() {
        if (arrows > 0) {
            arrows--;
            System.out.println("Loves!");
        } else {
            System.out.println("Nincsenek több nyilaid!");
        }
    }
}
