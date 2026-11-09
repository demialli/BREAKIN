package org.uob.a1.gameobjects;

public class Exit {

    private String name;
    private Room destination;
    private boolean locked;
    private Item key;
    
    public Exit(String name, Room destination, boolean locked) {
        this.name = name;
        this.destination = destination;
        this.locked = locked;
    }

    // todo: how to store which item unlocks it?
}
