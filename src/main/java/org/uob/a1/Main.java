// package declaration has to go above... 
package org.uob.a1; 

import org.uob.a1.gameobjects.*;
import org.uob.a1.parser.Parser;
import java.util.Scanner;

public class Main {

    static int endings = 0;
    
    // needs this throws interruptedexception bc java is annoying
    public static void main(String[] args) throws InterruptedException {
        // TODO: start the game and keep requesting user input until they quit

        // object declarations
        Scanner scanner = new Scanner(System.in);
        // put in a method so i can reset the game in the loop
        Map map = resetGame();

        // todo: help message if no command entered after a certain time
        // next milestone...

        // main loop
        while (map.isRunning()) {
            String input = stripInput(scanner.nextLine()); // still needs checking lol
            
            // handle exit here, otherwise always pass
            // nvm lol
            
            String result = Parser.parse(map, input);
            if (input.equals("quit") || input.equals("exit")) {
                System.out.println(result);
                return; // to avoid printing the last >>
            } else {
                System.out.println(result);
            }
            if (!map.isRunning()) {
                return; // check for quitting
            }

            // checking for ending move
            Exit ending = map.getR1().getExit("north"); // i called it final before whoops
            if (map.getCurrentRoom() == map.getR1() && ending != null && !ending.isLocked()) {
                // 5 seconds to memorise the code... is that too short?
                for (int i = 0; i < 5; i++) {
                    print(".");
                    Thread.sleep(1000);
                }
                
                System.out.print("\033[H\033[2J");
                System.out.flush(); // clear console >:)
                
                print("> Finally, you've made it to the door of the safe you came here for. Everything depends on this.");
                print("> You see a number keypad next to the door. It must need that code you saw earlier! Now what was it?\n");
                System.out.print(">> ");
                
                String urCode = scanner.nextLine().toLowerCase().trim();
                if (urCode.equals(map.getCode())) { // correct code
                    map.getCurrentScore().updateMove();
                    map.setCurrentRoom(map.getR3());
                    System.out.printf("You enter the code %s.%n", urCode);
                    sleep();
                    
                    printOn("A second passes, ");
                    printOn("then two, ");
                    printOn("then the keypad beeps green, and the safe door groans open.\n");
                    print("You push open the door, and step inside, and you can't believe it.");
                    print("💎  It's an enormous room filled floor to ceiling with gold and gemstones. 🪎\n"); // i love emojis
                    print(map.getCurrentRoom().getDesc());
                    sleep();
                    
                    if (endings < 2) {
                        endings++;
                    }
                    System.out.printf("GOOD ENDING (%d/2)%n", endings);
                    System.out.printf("FINAL SCORE: %d%n", map.getCurrentScore().getCurrentScore());
                    // at some point, add time left for score multiplier?
                    
                } else if (urCode.equals("quit") || urCode.equals("exit")) { // quit or exit here if u want
                    System.out.println("> Goodbye!\n");
                    map.setRunning(false);
                    break;
                    
                } else { // WRONG code
                    ending.setLocked(true); // not really necessary but it's funny to imagine irl
                    System.out.printf("You enter the code %s.%n", urCode);
                    sleep();
                    
                    printOn("A second passes, ");
                    printOn("then two, ");
                    printOn("then the starts flashing red and beeping loudly.\n");
                    print("Wrong code! You've set off the home's alarm system!");
                    map.getCurrentScore().failScore();
                    // update with try to escape in a short amount of time?
                    print("Good luck getting out!\n");
                    
                    if (endings < 2) {
                        endings++;
                    }
                    System.out.printf("BAD ENDING (%d/2)%n", endings);
                    System.out.printf("FINAL SCORE: %d%n", map.getCurrentScore().getCurrentScore());
                }
                
                System.out.print("\n> Play again? (y/n)\n>> ");
                input = stripInput(scanner.nextLine());
                
                if (input.toLowerCase().equals("y")) {
                    print("Resetting...");
                    map = resetGame();
                    sleep();
                    continue;
                } else {
                    print("> Goodbye!\n");
                    map.setRunning(false);
                    return;
                }
            }

            if (map.isRunning()) { // I GOT IT
                System.out.print("\n>> "); // prompter
            }
        }
    }

    public static String stripInput(String text) {
        return text.toLowerCase().trim().replaceAll("[^a-zA-Z ]", "");
    }
    
    public static void sleep() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void sleep1() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void print(String text) throws InterruptedException { // i miss python
        System.out.println(text);
        sleep();
    }

    public static void printOn(String text) throws InterruptedException {
        System.out.print(text);
        sleep1();
    }

    public static Map resetGame() throws InterruptedException {
        Map map = new Map();
        Player player = map.getPlayer();
        map.setRunning(true); // case! careful when naming instances after classes

        // intro
        print("\n> After weeks of planning and staking out a mansion, you've decided tonight is the night to make your move and break into the safe.");
        
        print("> Unfortunately, after some minor setbacks, you've arrived later than you were planning.");
        
        print("> But, it's now or never. You've managed to break in quietly through an open window in the kitchen.");
        
        print("> You have one hour to make it to the safe before the owners wake up.");

        System.out.println("\n> Welcome to BREAKIN!");
        System.out.print("> What will you do? \n\n>> ");
        return map;
    }

}
