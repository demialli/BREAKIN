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
        Room R1 = new Room("R1", "A dark hallway, with doors leading to other rooms. There's a stool against the wall.");
        Room R2 = new Room("R2", "The living room. You see a small watch on the coffee table.");
        Room R3 = new Room("R3", "Your bedroom, lit with fairy lights. It's past your bedtime.");
        Room R4 = new Room("R4", "A blue tiled family bathroom. You remember your friend's room being nearby.");
        Room R5 = new Room("R5", "A cosy study. You see a key glinting on a hook by the door.");
        Room[] rooms = new Room{R0, R1, R2, R3, R4, R5}; // is this necessary?
        // ^^ have to change premise... why would your bedroom be locked??
        // maybe a hotel or smth :/

        // exits
        Exit R0_EAST_R2 = new Exit("R0_EAST_R2", "R0", "R2", false);
        Exit R0_WEST_R1 = new Exit("R0_WEST_R1", "R0", "R1", false);
        Exit R1_NORTH_R3 = new Exit("R1_NORTH_R3", "R1", "R3", true);
        Exit R1_SOUTH_R2 = new Exit("R1_SOUTH_R2", "R1", "R2", false);
        Exit R1_EAST_R0 = new Exit("R1_EAST_R0", "R1", "R0", false);
        Exit R1_WEST_R4 = new Exit("R1_WEST_R4", "R1", "R4", true);
        Exit R2_NORTH_R1 = new Exit("R2_NORTH_R1", "R2", "R1", false);
        Exit R2_SOUTH_R0 = new Exit("R2_SOUTH_R0", "R2", "R0", false);
        Exit R2_WEST_R5 = new Exit("R2_WEST_R5", "R2", "R5", false);
        Exit R3_SOUTH_R1 = new Exit("R3_SOUTH_R1", "R3", "R1", false);
        Exit R4_EAST_R1 = new Exit("R4_EAST_R1", "R4", "R1", false);
        Exit R5_EAST_R2 = new Exit("R5_EAST_R2", "R5", "R2", false);

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