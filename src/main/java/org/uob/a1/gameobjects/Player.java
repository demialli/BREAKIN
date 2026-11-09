package org.uob.a1.gameobjects;

public class Player {

    private Item watch = new Item("watch", "A kids' watch with a glow in the dark face. You can vaguely make out the time.", "all", "You check the time on the watch.", "item", "all");
    // ^^ what do i put for non item slot items??
    
    private Item stool = new Item("stool", "A small stool you can use to reach a door handle.", "R1", "You step on the stool, allowing you to just reach the bathroom door's handle.", "item_slot", "R1_WEST_R4");
    private Item key = new Item("key", "A key with a ribbon, used to unlock a bedroom door.", "R1", "item_slot", "R1_NORTH_R3");
    private Item[] items = new Item[]{watch, stool, key};
    
    public Player() {
        // todo
    }

    public void addItem() {
        
    }

    public void getItems() {
        return items;
    }

    public void getItem(String name) {
        
    }

    public void showInventory() {
        System.out.println("Items in your inventory + [" + items.size() + "]:");
        for (Item item : items) {
            System.out.println(items.indexOf(item) + ": " + item.getName());
        }
    }
}
