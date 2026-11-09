// package declaration has to go above... 
package org.uob.a1; 

import org.uob.a1.gameobjects.Map;
import org.uob.a1.parser.Parser;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO: start the game and keep requesting user input until they quit

        // object declarations
        Scanner scanner = new Scanner(System.in);
        Map map = new Map();
        Map.setRunning(true);

        // intro
        System.out.println("After weeks of planning and staking out a mansion, you've decided tonight is the night to make your move and break into the safe.");
        Thread.sleep(1000);
        System.out.println("Unfortunately, after some minor setbacks, you've arrived later than you were planning.");
        Thread.sleep(1000);
        System.out.println("But, it's now or never. You've managed to break in quietly through an open window in the kitchen. You have one hour to make it to the safe before the owners wake up.");
        Thread.sleep(1000);
        System.out.print("What will you do? >> ");

        // main loop
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            
            // handle exit here, otherwise always pass
            if (input == "exit" || input == "quit") {
                System.out.println("Quitting...");
                Map.setRunning(false);
                return;
            } else {
                Object result = Parser.parse(map, input);
                // check for npc dialogue, which should be formatted nicely
                if (result instanceof String[] lines) { // checks for same type
                    for (String line : lines) {
                        System.out.println(line);
                        Thread.sleep(1000);
                    }
                } else {
                    // else print as normal
                    System.out.println(result);
                }
            }
        }
    }

}
