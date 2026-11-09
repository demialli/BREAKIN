import java.util.*;

package org.uob.a1.gameobjects;

public class Room {

    private String name;
    private String description;
    private ArrayList<String> exits;
    private ArrayList<String> items;
    private ArrayList<String> npcs;
    // ^^ arraylists for variable size
    
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addExit(Exit exit) {
        exits.add(exit);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addNpc(Npc npc) {
        npcs.add(npc);
    }

    // idk how to do these
    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }
    
    public Exit getExit(String direction) {
        
    }

    public Item getItem( ) {
        
    }

    public Npc getNpc() {
        
    }

    public String toString() { 
        String format = "Name: " + name + "/nDescription: " + description + "Exits: ";
        for (String exit : exits) {
            format += (exit.name + ", "); // maybe change later
        }
        format = format.substring(0, format.length() - 2) + "\nItems: ";
        for (String item : items) {
            format += (item.name + ", ");
        }
        format = format.substring(0, format.length() - 2) + "\nNPCs: ";
        for (String npc : npcs) {
            format += (npc.name + ", ");
        }
        return format.substring(format.length() - 2);
    }
}
