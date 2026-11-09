package org.uob.a1.gameobjects;

public class Exit {

    private String name;
    private Room source;
    private Room destination;
    private boolean locked;
    private Item key;
    
    public Exit(String name, Room source, Room destination, boolean locked) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.locked = locked;
    }

    // todo: how to store which item unlocks it?
    // like this! so it's optional
    public void addKey(Item key) {
        this.key = key;
    }

    // getters
    public String getName() {
        return name;
    }

    public Room getSource() {
        return source;
    }

    public Room getDestination() {
        return destination;
    }

    public boolean isLocked() {
        return locked;
    }

    // idk what to do w this
    /* public Item getKey() {
        if (key) {
            return key;
        }
    } */

    // setters
    // the more setters i make, the more i realise i'll never really need them...
    public void setName(String name) {
        this.name = name;
    }

    public void setSource(Room source) {
        this.source = source;
    }

    public void setDestination(Room destination) {
        this.destination = destination;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setKey(Item key) {
        this.key = key;
    }
}
