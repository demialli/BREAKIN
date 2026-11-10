// package declaration has to go above... 
package org.uob.a1; 

import org.uob.a1.gameobjects.Map;
import org.uob.a1.parser.Parser;
import java.util.Scanner;

public class Main {

    // needs this throws interruptedexception bc java is annoying
    public static void main(String[] args) throws InterruptedException {
        // TODO: start the game and keep requesting user input until they quit

        // object declarations
        Scanner scanner = new Scanner(System.in);
        Map map = new Map();
        map.setRunning(true); // case! careful when naming instances after classes you fool

        // intro
        System.out.println("> After weeks of planning and staking out a mansion, you've decided tonight is the night to make your move and break into the safe.");
        sleep();
        
        System.out.println("> Unfortunately, after some minor setbacks, you've arrived later than you were planning.");
        sleep();
        
        System.out.println("> But, it's now or never. You've managed to break in quietly through an open window in the kitchen. You have one hour to make it to the safe before the owners wake up.");
        sleep();
        
        System.out.print("\n> What will you do? \n>> ");

        // main loop
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            
            // handle exit here, otherwise always pass
            if (input.equals("exit") || input.equals("quit")) { // .equals() for strings
                System.out.println("> Goodbye!");
                map.setRunning(false);
                break; // break, not return lol
            } else {
                Object result = Parser.parse(map, input);
                // check for npc dialogue, which should be formatted nicely
                System.out.println("\n" + result);
                System.out.print(">> ");
                /* if (result instanceof String[] lines) { // checks for same type
                    for (String line : lines) {
                        System.out.println(line);
                        Thread.sleep(1000);
                    }
                } else {
                    // else print as normal
                    System.out.println(result);
                } */ // needs to return String always :/
            }
        }
    }

    public static void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

}
