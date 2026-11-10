// package declaration has to go above... 
package org.uob.a1; 

import org.uob.a1.gameobjects.*;
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
        print("> After weeks of planning and staking out a mansion, you've decided tonight is the night to make your move and break into the safe.");
        sleep();
        
        print("> Unfortunately, after some minor setbacks, you've arrived later than you were planning.");
        sleep();
        
        print("> But, it's now or never. You've managed to break in quietly through an open window in the kitchen. You have one hour to make it to the safe before the owners wake up.");
        sleep();
        
        System.out.print("\n> What will you do? \n>> ");

        // todo: help message if no command entered after a certain time

        // main loop
        while (map.isRunning()) {
            String input = scanner.nextLine().toLowerCase().trim();
            
            // handle exit here, otherwise always pass
            if (input.equals("exit") || input.equals("quit")) { // .equals() for strings
                print("> Goodbye!\n");
                map.setRunning(false);
                break; // break, not return lol
            }
            
            String result = Parser.parse(map, input);
            print(result);

            // checking for ending move
            Exit ending = map.getR1().getExit("NORTH"); // i called it final before whoops
            if (map.getCurrentRoom() == map.getR1() && ending != null && !ending.isLocked()) {
                // 5 seconds to memorise the code... is that too short?
                for (int i = 0; i < 5; i++) {
                    print(".");
                    Thread.sleep(1000);
                }
                System.out.print("\033[H\033[2J");
                System.out.flush(); // clear console >:)
                
                print("> Finally, you've made it to the door of the safe you came here for. Everything depends on this.");
                sleep();
                print("> You see a number keypad next to the door. It must need that code you saw earlier! Now what was it?\n");
                sleep();
                System.out.print(">> ");
                String urCode = scanner.nextLine().toLowerCase().trim();
                if (urCode.equals(map.getPlayer().getCode())) { // correct code
                    map.getCurrentScore().updateMove();
                    map.setCurrentRoom(map.getR3());
                    System.out.printf("You enter the code %s.%n", urCode);
                    sleep();
                    print("A second passes, then two, then the keypad beeps green, and the safe door groans open.");
                    sleep();
                    print("You push open the door, and step inside, and you can't believe it.");
                    sleep();
                    print("It's an enormous room filled floor to ceiling with gold and gemstones.\n");
                    sleep();
                    print(map.getCurrentRoom().getDesc());
                    sleep();
                    print("GOOD ENDING");
                    System.out.printf("FINAL SCORE: %d%n", map.getCurrentScore().getCurrentScore());
                    // at some point, add time left for score multiplier?
                    map.setRunning(false);
                    return;
                } else if (urCode.equals("quit") || urCode.equals("exit")) { // quit or exit for whatever reason
                    print("> Goodbye!\n");
                    map.setRunning(false);
                    break;
                } else { // WRONG code
                    ending.setLocked(true); // not really necessary but it's funny to imagine irl
                    System.out.printf("You enter the code %s.%n", urCode);
                    sleep();
                    print("A second passes, then two, then the keypad starts flashing red and beeping loudly.");
                    sleep();
                    print("Wrong code! You've set off the home's alarm system!");
                    map.getCurrentScore().failScore();
                    // update with try to escape in a short amount of time?
                    sleep();
                    print("Good luck getting out!\n");
                    sleep();
                    print("BAD ENDING");
                    System.out.printf("FINAL SCORE: %d%n", map.getCurrentScore().getCurrentScore());
                    map.setRunning(false);
                    return;
                }
            }

            System.out.print("\n>> ");
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

    public static void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void print(String text) { // i miss python
        System.out.println(text);
    }

}
