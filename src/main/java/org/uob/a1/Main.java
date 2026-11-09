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
        System.out.println("intro (change later)");
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
                Parser.parse(map, input);
            }
        }
    }

}
