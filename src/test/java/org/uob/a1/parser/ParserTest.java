package org.uob.a1.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.uob.a1.gameobjects.Exit;
import org.uob.a1.gameobjects.Item;
import org.uob.a1.gameobjects.Map;
import org.uob.a1.gameobjects.Npc;
import org.uob.a1.gameobjects.Room;

// Every command goes in through parser.parse and results are checked through
// its replies or the current room, so it does not matter which class does the
// actual work.
class ParserTest {

    private final Parser parser = new Parser();

    // look

    @Test
    void lookTest() {
        Room room = new Room("hub", "A hub room.");
        Room next = new Room("next", "desc");
        room.addExit(new Exit("north", next, false));
        room.addItem(new Item("scanner", "desc", room, "text", "tool", null));
        room.addNpc(new Npc("guard", "Halt!"));
        Map map = new Map(room);

        String result = parser.parse(map, "look");

        assertTrue(result.contains("hub"));
        assertTrue(result.contains("A hub room."));
        assertTrue(result.contains("north"));
        assertTrue(result.contains("scanner"));
        assertTrue(result.contains("guard"));
    }

    @Test
    void lookItemTest() {
        Room room = new Room("hub", "desc");
        room.addItem(new Item("scanner", "A handheld scanner.", room, "text", "tool", null));
        Map map = new Map(room);

        assertTrue(parser.parse(map, "look scanner").contains("A handheld scanner."));
    }

    @Test
    void lookUnknownItemTest() {
        Room room = new Room("hub", "desc");
        Map map = new Map(room);

        assertNotNull(parser.parse(map, "look scanner"));
    }

    // move

    @Test
    void moveTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        start.addExit(new Exit("north", next, false));
        Map map = new Map(start);

        parser.parse(map, "move north");

        assertEquals(next, map.getCurrentRoom());
    }

    @Test
    void moveMixedCaseTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        start.addExit(new Exit("north", next, false));
        Map map = new Map(start);

        parser.parse(map, "Move North");

        assertEquals(next, map.getCurrentRoom());
    }

    @Test
    void moveNoSuchExitTest() {
        Room start = new Room("hub", "desc");
        Map map = new Map(start);

        assertNotNull(parser.parse(map, "move north"));
        assertEquals(start, map.getCurrentRoom());
    }

    @Test
    void moveWithoutDirectionTest() {
        Room start = new Room("hub", "desc");
        Map map = new Map(start);

        assertNotNull(parser.parse(map, "move"));
        assertEquals(start, map.getCurrentRoom());
    }

    @Test
    void moveLockedExitTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        start.addExit(new Exit("north", next, true));
        Map map = new Map(start);

        parser.parse(map, "move north");

        assertEquals(start, map.getCurrentRoom());
    }

    // take and inventory

    @Test
    void takeTest() {
        Room room = new Room("hub", "desc");
        room.addItem(new Item("scanner", "desc", room, "text", "tool", null));
        Map map = new Map(room);

        parser.parse(map, "take scanner");

        assertTrue(parser.parse(map, "inventory").contains("scanner"));
        assertFalse(parser.parse(map, "look").contains("scanner"));
    }

    @Test
    void getTest() {
        Room room = new Room("hub", "desc");
        room.addItem(new Item("scanner", "desc", room, "text", "tool", null));
        Map map = new Map(room);

        parser.parse(map, "get scanner");

        assertTrue(parser.parse(map, "inventory").contains("scanner"));
    }

    @Test
    void takeUnknownItemTest() {
        Room room = new Room("hub", "desc");
        Map map = new Map(room);

        assertNotNull(parser.parse(map, "take scanner"));
        assertFalse(parser.parse(map, "inventory").contains("scanner"));
    }

    // use

    @Test
    void useUnlocksExitTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        Exit exit = new Exit("north", next, true);
        start.addExit(exit);
        start.addItem(new Item("key", "desc", start, "It clicks open.", "key", exit));
        Map map = new Map(start);

        parser.parse(map, "take key");
        String result = parser.parse(map, "use key");
        parser.parse(map, "move north");

        assertTrue(result.contains("It clicks open."));
        assertEquals(next, map.getCurrentRoom());
    }

    @Test
    void activateTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        Exit exit = new Exit("north", next, true);
        start.addExit(exit);
        start.addItem(new Item("key", "desc", start, "It clicks open.", "key", exit));
        Map map = new Map(start);

        parser.parse(map, "take key");
        parser.parse(map, "activate key");
        parser.parse(map, "move north");

        assertEquals(next, map.getCurrentRoom());
    }

    @Test
    void useItemNotCarriedTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        Exit exit = new Exit("north", next, true);
        start.addExit(exit);
        start.addItem(new Item("key", "desc", start, "It clicks open.", "key", exit));
        Map map = new Map(start);

        parser.parse(map, "use key");
        parser.parse(map, "move north");

        assertEquals(start, map.getCurrentRoom());
    }

    @Test
    void useInWrongRoomTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        Room other = new Room("other", "desc");
        Exit exit = new Exit("north", next, true);
        start.addExit(exit);
        start.addItem(new Item("key", "desc", other, "It clicks open.", "key", exit));
        Map map = new Map(start);

        parser.parse(map, "take key");
        parser.parse(map, "use key");
        parser.parse(map, "move north");

        assertEquals(start, map.getCurrentRoom());
    }

    // talk

    @Test
    void talkTest() {
        Room room = new Room("hub", "desc");
        room.addNpc(new Npc("guard", "Halt!"));
        Map map = new Map(room);

        assertTrue(parser.parse(map, "talk guard").contains("Halt!"));
    }

    @Test
    void talkToTest() {
        Room room = new Room("hub", "desc");
        room.addNpc(new Npc("guard", "Halt!"));
        Map map = new Map(room);

        assertTrue(parser.parse(map, "talk to guard").contains("Halt!"));
    }

    @Test
    void talkUnknownNpcTest() {
        Room room = new Room("hub", "desc");
        Map map = new Map(room);

        assertNotNull(parser.parse(map, "talk guard"));
    }

    // score

    @Test
    void scoreTest() {
        Room room = new Room("hub", "desc");
        Map map = new Map(room);

        assertTrue(parser.parse(map, "score").contains("100"));
    }

    @Test
    void scoreAfterMoveTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        start.addExit(new Exit("north", next, false));
        Map map = new Map(start);

        parser.parse(map, "move north");

        assertTrue(parser.parse(map, "score").contains("99"));
    }

    @Test
    void scoreAfterUnlockTest() {
        Room start = new Room("hub", "desc");
        Room next = new Room("next", "desc");
        Exit exit = new Exit("north", next, true);
        start.addExit(exit);
        start.addItem(new Item("key", "desc", start, "It clicks open.", "key", exit));
        Map map = new Map(start);

        parser.parse(map, "take key");
        parser.parse(map, "use key");

        assertTrue(parser.parse(map, "score").contains("110"));
    }

    // quit and unknown commands

    @Test
    void quitTest() {
        Room room = new Room("hub", "desc");
        Map map = new Map(room);

        parser.parse(map, "quit");

        assertFalse(map.isRunning());
    }

    @Test
    void exitTest() {
        Room room = new Room("hub", "desc");
        Map map = new Map(room);

        parser.parse(map, "exit");

        assertFalse(map.isRunning());
    }

    @Test
    void unknownCommandTest() {
        Room room = new Room("hub", "desc");
        Map map = new Map(room);

        assertNotNull(parser.parse(map, "dance"));
        assertEquals(room, map.getCurrentRoom());
        assertTrue(map.isRunning());
    }
}
