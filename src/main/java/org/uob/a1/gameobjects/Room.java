import java.util.*;

package org.uob.a1.gameobjects;

public class Room {

    private String name;
    private String description;
    private ArrayList<String> exits;
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

    public String toString() { 
        String format = "Name: " + name + "/nDescription: " + description + "Exits: ";
        for (String exit : exits) {
            format += (exit.getName() + ", "); 
        }
        if (item) {
            format = format.substring(0, format.length() - 2) + "\nItem: " + item.getName();    
        }
        if (npc) {
            format = format.substring(0, format.length() - 2) + "\nNPC: " + npc.getName();
        }
        return format.substring(format.length() - 2);
    }
}
