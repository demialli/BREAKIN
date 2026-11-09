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

    public String parse(Map map, String input) {
        // TODO: work out which command was entered, execute it and return the result
        Scanner scanner = new Scanner(System.in); 
        

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

        String command = scanner.nextLine().toLower();
        String regex = "[s]"; // from w3schools
        String[] commandWords = command.split(regex);


        // first command word in acceptable list: check length of command
        if (Arrays.asList(LEGALCOMMANDS).contains(commandWords[0])) {
            String first = commandWords[0];

            // one word commands
            if (commandWords.length == 1) {
                switch(first) {
                    case "look":
                        // run look
                        break;
                    case "inventory":
                        // run inventory
                        break;
                    case "score":
                        // run score
                        break;
                    case "help":
                        // run help
                        break;
                    case "quit":
                    case "exit:" // fall through for ||
                        break;
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
}
