package org.uob.a1.gameobjects;

public class Map {

    private Score score = new Score();
    private Player player;
    private Room currentRoom;
    private boolean running = true;
    public Room R0, R1, R2, R3, R4, R5; // need to be global

    private String code = String.format("%04d", (int)(Math.random() * 10000)); // random 4 digit code 
    // ^^ longer codes to increase difficulty
    private Item watch;
    private Item key;
    private Item diary; 
    
    public Map(Room startingRoom) {
        currentRoom = startingRoom;
        player = new Player(this);
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
        R0.addExit(new Exit("east", R2, false));
        R0.addExit(new Exit("west", R1, false));

        R1.addExit(new Exit("north", R3, true));
        R1.addExit(new Exit("south", R2, false));
        R1.addExit(new Exit("east", R0, false));
        R1.addExit(new Exit("west", R4, true));
        // ofc u dont need the source lol the object is the source

        R2.addExit(new Exit("north", R1, false));
        R2.addExit(new Exit("south", R0, false));
        R2.addExit(new Exit("west", R5, false));
        R2.addNpc(new Npc("burglar", "👁  Your colleague, who's helping you rob this house.\n< I'm pretty sure the safe is nearby, but you might've gone in the opposite direction. >\n< There might be something that can help in the next room over. >"));
        
        R3.addExit(new Exit("south", R1, false));
        R4.addExit(new Exit("east", R1, false));
        R5.addExit(new Exit("east", R2, false));
        // it did say exit names are lowercase tbf...
        
        player = new Player(this); 
        currentRoom = R0;
        
        // moving item creation here...
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

    public String getCode() {
        return code;
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