package org.example;

public class Gold {
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

