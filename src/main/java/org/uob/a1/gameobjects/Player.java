// continue here!!

package org.uob.a1.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Map map;

    private String code = String.format("%04d", (int)(Math.random() * 10000)); // random 4 digit code 
    // ^^ longer codes to increase difficulty
    private Item watch;
    private Item key;
    private Item diary; 
    private ArrayList<Item> items = new ArrayList<>();
    // ^^ add existing items to this list as theyre collected
    
    
    public Player(Map map) {
        // dont really need to put anything here...
        // not true!
        this.map = map;
        Room R1 = map.getR1();
        Room R2 = map.getR2();
        Room R5 = map.getR5();
        
        watch = new Item(
            "watch", 
            "An antique pocket watch. You can hear it ticking softly in the silence.", 
            R2, // should technically be all, but idk what to do with it for now
            "You click it open and check the time on its luminescent face.", 
            "resource", 
            null);
        // ^^ what do i put for non item slot items?? theyre used in all rooms...
        
        key = new Item(
            "key", 
            "A key with on ribbon, used to unlock a study's door.", 
            R1, 
            "You turn the key in the lock quietly, unlocking the weathered door.\n> WEST exit unlocked", 
            "key", 
            R1.getExit("west"));
        
        diary = new Item(
            "diary", 
            "A well-loved diary with a hand-painted cover. There might be something important inside.", 
            R1, 
            "You flip it open to its bookmarked page, where a code has been hastily scribbled: (" + code + "). That's probably important.\n> NORTH exit unlocked", 
            "key", 
            R1.getExit("north"));
        // need to implement code entering when unlocking R3!

        // add items to rooms
        R1.addItem(key);
        R2.addItem(watch);
        R5.addItem(diary);
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

    public String getCode() {
        return code;
    }

    public String showInventory() {
        if (items.isEmpty()) {
            return "Your inventory is empty.";
        } else {
            String format = "Items in your inventory + [" + items.size() + "]:\n";
            for (Item item : items) {
                format += (items.indexOf(item) + ": " + item.getName() + "\n");
            }
            return format;
        }
    }

    public static Exit move(String direction, Room currentRoom) {
        return currentRoom.getExit(direction);
    }
}
