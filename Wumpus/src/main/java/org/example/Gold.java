package org.example;
import java.io.Serializable;
public class Gold implements Serializable{
    private boolean isCollected;

    public Gold() {
        this.isCollected = false;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void collect() {
        this.isCollected = true;
    }
}
