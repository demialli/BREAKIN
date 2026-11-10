// continue here!!

package org.uob.a1.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Map map;
    private ArrayList<Item> items = new ArrayList<>();
    // ^^ add existing items to this list as theyre collected
    
    
    public Player(Map map) {
        // dont really need to put anything here...
        // not true!
        this.map = map;
        // yes true?
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) { // EQUALS FOR STRINGSSS
                return item;
            }
        }
        return null;
    }

    public String showInventory() {
        if (items.isEmpty()) {
            return "Your inventory is empty.";
        } else {
            String format = "Items in your inventory [" + items.size() + "]:\n";
            for (Item item : items) {
                format += ((items.indexOf(item) + 1) + ": " + item.getName() + "\n");
            }
            return format;
        }
    }

    public static Exit move(String direction, Room currentRoom) {
        return currentRoom.getExit(direction);
    }
}
