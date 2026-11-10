package org.uob.a1.gameobjects;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private Map map;
    
    private Item watch;
    private Item code;
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
        
        watch = new Item(
            "watch", 
            "An antique pocket watch. You click it open and notice the face is luminescent - you can vaguely make out the time.", 
            R2, // should technically be all, but idk what to do with it for now
            "You check the time on the watch.", 
            "item", 
            null);
        // ^^ what do i put for non item slot items??
    
        String code = String.format("%04d", (int)(Math.random() * 10000)); // random 4 digit code
        
        key = new Item(
            "stool", 
            "A small stool you can use to reach a door handle.", 
            R1, 
            "You step on the stool, allowing you to just reach the bathroom door's handle.", 
            "item_slot", 
            R1.exits.get(0));
        
        diary = new Item(
            "key", 
            "A key with a ribbon, used to unlock a bedroom door.", 
            R1, 
            "You flip it open to its bookmarked page, where a code has been hastily scribbled: (" + code + ")", 
            "item_slot", 
            R1.exits.get(0));
        // need to implement code entering when unlocking R3!
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName() == name) {
                return item;
            }
        }
        return null;
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

    public void move(String direction) {
        // todo
    }
}
