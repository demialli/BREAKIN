package org.uob.a1.gameobjects;

import java.util.*; // imports go under package yeah yeah

public class Room {

    private String name;
    private String description;
    public ArrayList<Exit> exits = new ArrayList<>(); // just gonna make it public and see what happens
    // each room only has 1 item/npc max, so they dont need to be arrays
    private Item item = null;
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

    // getters
    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }
    
    public Exit getExit(String direction) {
        for (Exit exit : exits) {
            if (exit.getName().equalsIgnoreCase(direction)) {
                return exit;
            }
        }
        return null;
    }

    // for checking
    public ArrayList<Exit> getExits() {
        return exits;
    }

    public Item getItem() {
        return item;
    }

    public Npc getNpc() {
        return npc;
    }

    public String toString(Room currentRoom) { // NEW! fun but excessively long formatting
        String desc = currentRoom.getDesc();
        String format = String.format("You're in %s. %s%n", currentRoom.getName(), desc);
        
        ArrayList<Exit> exits = currentRoom.getExits();
        int s = exits.size();
        
        if (s == 1) {
            format += String.format("! There's an exit to the %s.", exits.get(0).getName());
        } else if (s == 2) {
            format += String.format("! There are exits to the %s and %s.", exits.get(0).getName(), exits.get(1).getName());
        } else if (s > 2) {
            format += "! There are exits to the ";
            for (int i = 0; i < s - 1; i++) {
                format += exits.get(i).getName() + ", ";
            }
            format += String.format("and %s.", exits.get(exits.size() - 1).getName());
        }
        
        Item item = currentRoom.getItem();
        if (item != null) {
            format += String.format("%n! There's a %s here.", item.getName());
        }
        Npc npc = currentRoom.getNpc();
        if (npc != null) {
            format += String.format("%n! You can talk to a %s here.", npc.getName());
        }
        
        return format;
    }

    private static String toTitleCase(String string) {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1).toLowerCase();
    } // dont need this...
}
