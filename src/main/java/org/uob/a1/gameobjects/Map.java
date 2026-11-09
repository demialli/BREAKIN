// todo: everything

package org.uob.a1.gameobjects;

public class Map {

    Player player;
    Room currentRoom;
    Score score;
    
    
    public Map(Room startingRoom) {
        // TODO
        // what do i do with this?? what do i pass if the rooms are defined here?
    }

    public Map() {
        // TODO: set up the rooms based on map/layout.txt

        // rooms
        Room R0 = new Room("R0", "An empty kitchen in your new house.");
        R0.addExit(new Exit("EAST", "R0", "R2", false));
        R0.addExit(new Exit("WEST", "R0", "R1", false));
        
        Room R1 = new Room("R1", "A dark hallway, with doors leading to other rooms. There's a stool against the wall.");
        R1.addExit(new Exit("NORTH", "R1", "R3", true));
        R1.addExit(new Exit("SOUTH", "R1", "R2", false));
        R1.addExit(new Exit("EAST", "R1", "R0", false));
        R1.addExit(new Exit("WEST", "R1", "R4", true));
        
        Room R2 = new Room("R2", "The living room. You see a small watch on the coffee table.");
        R2.addExit(new Exit("NORTH", "R2", "R1", false));
        R2.addExit(new Exit("SOUTH", "R2", "R0", false));
        R2.addExit(new Exit("WEST", "R2", "R5", false));
        
        Room R3 = new Room("R3", "Your bedroom, lit with fairy lights. It's past your bedtime.");
        R3.addExit(new Exit("SOUTH", "R3", "R1", false));
        
        Room R4 = new Room("R4", "A blue tiled family bathroom. You remember your friend's room being nearby.");
        R4.addExit(new Exit("EAST", "R4", "R1", false));
        
        Room R5 = new Room("R5", "A cosy study. You see a key glinting on a hook by the door.");
        R5.addExit(new Exit("EAST", "R5", "R2", false));
        
        Room[] rooms = new Room{R0, R1, R2, R3, R4, R5}; // is this necessary?
        // ^^ have to change premise... why would your bedroom be locked??
        // maybe a hotel or smth :/

        // locked:
        // r1 north r3
        // r1 west r4
    }
    

    // getters
    public boolean isRunning() {
        // TODO
        return running;
    }

    public Room getCurrentRoom() {
        // TODO
        return currentRoom;
    }

    public Score getCurrentScore() {
        return score;
    }
    

    // setters
    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setCurrentRoom(Room room) {
        this.room = room;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}