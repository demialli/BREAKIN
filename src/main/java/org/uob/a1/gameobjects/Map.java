package org.uob.a1.gameobjects;

public class Map {

    private Score score = new Score();
    private Player player;
    private Room currentRoom;
    private boolean running = false;
    public Room R0, R1, R2, R3, R4, R5; // need to be global
    
    
    public Map(Room startingRoom) {
        currentRoom = startingRoom;
    }

    public Map() {
        // TODO: set up the rooms based on map/layout.txt

        
        
        // rooms
        R0 = new Room("R0", "An empty kitchen in a grand house. Moonlight streams in through the skylight. You shouldn't be here.");
        R1 = new Room("R1", "A spacious living room, with many plush armchairs. You see a small key left on the coffee table.");
        R2 = new Room("R2", "A long hallway, leading to another room. You see an antique pocket watch lying on a mantelpiece near the end. Your partner in crime is here.");
        R3 = new Room("R3", "You made it!");
        R4 = new Room("R4", "A cosy study. There's nothing useful in here, but you remember the safe's entrance being nearby.");
        R5 = new Room("R5", "A bedroom, where the owners of the house are sleeping peacefully. With the door ajar, you can just make out a small diary on the bedside table.");  // rooms have to be created first to avoid null errorssss
        
        // i have just realised the layout makes no sense and i was very confused about it for a bit. evil!! R0 east R2 but R2 south R0...
        R0.addExit(new Exit("EAST", R0, R2, false));
        R0.addExit(new Exit("WEST", R0, R1, false));

        R1.addExit(new Exit("NORTH", R1, R3, true));
        R1.addExit(new Exit("SOUTH", R1, R2, false));
        R1.addExit(new Exit("EAST", R1, R0, false));
        R1.addExit(new Exit("WEST", R1, R4, true));

        R2.addExit(new Exit("NORTH", R2, R1, false));
        R2.addExit(new Exit("SOUTH", R2, R0, false));
        R2.addExit(new Exit("WEST", R2, R5, false));
        R2.addNpc(new Npc("Burglar", "Your colleague, who's helping you rob this house.\n< I'm  pretty sure the safe is nearby, but you might've gone in the opposite direction. >\n< There might be something that can help in the next room over. >"));
        
        R3.addExit(new Exit("SOUTH", R3, R1, false));
        
        R4.addExit(new Exit("EAST", R4, R1, false));
        
        R5.addExit(new Exit("EAST", R5, R2, false));
        
        player = new Player(this); 
        currentRoom = R0;
    }
    

    // getters
    public boolean isRunning() {
        return running;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Score getCurrentScore() { // should probs change to getscore bc score has getcurrentscore lol
        return score;
    }

    public Player getPlayer() {
        return player;
    }

    // getters for each room
    public Room getR0() { return R0; }
    public Room getR1() { return R1; }
    public Room getR2() { return R2; }
    public Room getR3() { return R3; }
    public Room getR4() { return R4; }
    public Room getR5() { return R5; }
    

    // setters
    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}