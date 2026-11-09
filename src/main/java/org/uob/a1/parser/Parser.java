// todo: pass in command calls

package org.uob.a1.parser;

import org.uob.a1.gameobjects.Map;
import java.util.Scanner;
import java.util.Arrays;

public class Parser {

    public Parser() {}

    public String[] LEGALCOMMANDS = {"move", "look", "take", "get", "inventory", "use", 
                                  "activate", "talk", "score", "help", "quit", "exit"};
    public String[] LEGALCOMMANDSFULL = {"move <direction>", "look", "look/take <item>", "get <item>", "inventory",
                                         "use/activate <item>", "talk/talk to <npc>", "score", "help", "quit/exit"};

    Map map;
    String input;

    public String parse(Map map, String input) {
        // TODO: work out which command was entered, execute it and return the result        

        // commands todo:
        // move <direction>: move to different Room
        // look: display information about current Room
        // look <item>: display information of an Item in the room
        // take OR get <item>: take an Item from a Room and add to Inventory
        // inventory: view names of Items in Inventory
        // use OR activate <item>: use an Item in a Room to change something
            // ^^ needs reply text.
        // talk OR talk to <npc>: talk to an NPC
            // ^^ needs reply text.
        // score: display current score
        // help: display a help message
        // quit OR exit: exit the game
        // return a String based on the result of the commands
        // must be case insensitive
        // handle illegal/misspelled commands gracefully
        
        Player player = map.getPlayer;
        Room currentRoom = map.getCurrentRoom;
        Score score = map.getCurrentScore;
        
        String regex = "[s]"; 
        String[] commandWords = input.split(regex);

        // if first command word in acceptable list: check length of command
        if (Arrays.asList(LEGALCOMMANDS).contains(commandWords[0])) {
            String first = commandWords[0];

            // one word commands
            if (commandWords.length == 1) {
                switch(first) {
                    case "look":
                        System.out.println(currentRoom.getDesc());
                        break;
                    case "inventory":
                        player.showInventory();
                        break;
                    case "score":
                        System.out.printf("Your current score is (%d)%n", score.getCurrentScore())
                        break;
                    case "help": // should help messages be specific to rooms? a possible attribute to add...
                        help()
                        break;
                    // case "quit":
                    // case "exit:" // fall through for ||
                        // break;
                    // ^^ these are handled in main
                }
                
            // two word commands
            } else if (commandWords.length == 2) { 
                switch(first) {
                    case "move":
                        // run move
                        break;
                    case "look":
                        // run loop
                        break;
                    case "take":
                    case "get":
                        // run take
                        break;
                    case "use":
                    case "activate":
                        // run use
                        break;
                }
            
            // only valid three word command is talk to npc
            } else if (commandWords.length == 3) {
                // run talk to npc
            }
            // any other length is invalid
            } else {
                invalidCommand();
            }
        } else { // misspelling or invalid: reject output
            invalidCommand();
        }
        
        return null;
    }

    public void invalidCommand() {
        System.out.println("Sorry, that's not a valid command. Here's a list of valid commands:");
        for (String command : LEGALCOMMANDSFULL) {
            System.out.println(command); // ^^ for x in array
        }
    }

    public void help() {
        System.out.println("Enter a command to continue the game. Here's a list of valid commands:");
        for (String command : LEGALCOMMANDSFULL) {
            System.out.println(command); // ^^ for x in array
        }
    }


}
