package org.uob.a1.gameobjects;

import java.util.*; // imports go under package yeah yeah

public class Room {

    private String name;
    private String description;
    public ArrayList<Exit> exits; // just gonna make it public and see what happens
    // each room only has 1 item/npc max, so they dont need to be arrays
    private Item item;
    private Npc npc;
    // ^^ arraylists for variable size
    
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public void addItem(Item item) {
        this.item = item;
    }

    public void addNpc(Npc npc) {
        this.npc = npc;
    }

    // idk how to do these
    // todo: finish these!
    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }
    
    public Exit getExit(String direction) {
        for (Exit exit : exits) {
            if (exit.getName() == direction) {
                return exit;
            }
        }
    }

    public Item getItem() {
        return item;
    }

    public Npc getNpc() {
        return npc;
    }

    public String toString() { // fun but excessively long formatting
        String format = "Name: " + name + "/nDescription: " + description + "Exits: ";
        for (Exit exit : exits) {
            format += (exit.getName() + ", "); 
        }
        if (item != null) {
            format = format.substring(0, format.length() - 2) + "\nItem: " + item.getName();    
        }
        if (npc != null) {
            format = format.substring(0, format.length() - 2) + "\nNPC: " + npc.getName();
        }
        return format.substring(format.length() - 2);
    }
}
