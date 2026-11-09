package org.uob.a1.gameobjects;

public class Map {

    private Player player;
    private Room currentRoom;
    private Score score;
    private boolean running;
    public Room R0, R1, R2, R3, R4, R5; // need to be global
    
    
    
    public Map(Room startingRoom) {
        currentRoom = startingRoom;
    }

    public Map() {
        // TODO: set up the rooms based on map/layout.txt

        // rooms
        R0 = new Room("R0", "An empty kitchen in a grand house.");
        R0.addExit(new Exit("EAST", R0, R2, false));
        R0.addExit(new Exit("WEST", R0, R1, false));
        
        R1 = new Room("R1", "A spacious living room, with many plush armchairs. You see a small key on a ribbon left on the coffee table.");
        R1.addExit(new Exit("NORTH", R1, R3, true));
        R1.addExit(new Exit("SOUTH", R1, R2, false));
        R1.addExit(new Exit("EAST", R1, R0, false));
        R1.addExit(new Exit("WEST", R1, R4", true));
        
        R2 = new Room("R2", "A long hallway, leading to another room. Your partner in crime is here.");
        R2.addExit(new Exit("NORTH", R2, R1, false));
        R2.addExit(new Exit("SOUTH", R2, R0, false));
        R2.addExit(new Exit("WEST", R2, R5, false));
        R2.addNpc(new Npc("Burglar", new String[]{"Your colleague, who's helping you rob this house.", "< I'm  pretty sure the safe is nearby, but you might've gone in the opposite direction. >", "< There might be something that can help in the next room over. >"}));
        
        R3 = new Room("R3", "When you manage to enter the code in the vault door, it gives way to an enormous room filled floor to ceiling with gold and gemstones. You made it!");
        R3.addExit(new Exit("SOUTH", R3, R1, false));
        
        R4 = new Room("R4", "A cosy study. There's nothing useful in here, but you remember the safe's entrance being nearby.");
        R4.addExit(new Exit("EAST", R4, R1, false));
        
        R5 = new Room("R5", "A bedroom, where the owners of the house are sleeping peacefully. With the door ajar, you can just make out a small diary on the bedside table.");
        R5.addExit(new Exit("EAST", R5, R2, false));
        
        Room[] rooms = new Room[]{R0, R1, R2, R3, R4, R5}; // is this necessary?
        // probs not but i'll keep it for now!
        currentRoom = R0;
    }
    

    // getters
    public  boolean isRunning() {
        return running;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Score getCurrentScore() {
        return score;
    }

    // getters for each room
    public Room getR0() { return R0; }
    public Room getR1() { return R1; }
    public Room getR2() { return R2; }
    public Room getR3() { return R3; }
    public Room getR4() { return R4; }
    public Room getR5() { return R5; }
    

    // setters
    public static void setRunning(boolean running) { // has to be static as called in static main
        this.running = running;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}