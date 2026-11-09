import java.util.Scanner;
package org.uob.a1;

public class Main {

    public static void main(String[] args) {
        // TODO: start the game and keep requesting user input until they quit
        
        // object instantiations
        Map map = new Map()
        
        Scanner scanner = new Scanner(System.in);
        
        Map.setRunning(true);

        System.out.println("intro (change later)");
        System.out.print("What will you do? >> ");
        
        while (true) {
            String input = scanner.nextLine().toLower();
            
            // handle exit here, otherwise always pass
            if (input == "exit" || input == "quit") {
                System.out.println("Quitting...");
                return;
            } else {
                Parser.parse(map, input);
            }
        }


}
