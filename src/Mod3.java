import java.util.Scanner;

/**
 * Class for the DFA over the language {x|x mod 3 = 0}
 * @author Carter Close
 * @version 1.0
 */
public class Mod3 {
    /**
     * Main method, defines everything and provides all logic
     * @param args for CLI
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        // First way to do it, vastly preferred by Dutter, uses a 2D array

        // Make transition function delta a LUT/2D array
        int[][] stateDiagram = {
                //0, 1
                {0, 1}, //state 0
                {2, 0}, //state 1
                {1, 2}, //state 2
        };

        int startState = 0;
        int acceptingState = 0;
        String inputString = "";
        int currentState;

        // Input handling
        System.out.println("Enter a binary string:");
        inputString = scnr.nextLine();
        // While loop keeps the program asking for a string until it receives an empty string (just the enter key)
        while(!inputString.isEmpty()) {
            currentState = startState;
            // Each element in the language being handled by a for loop
            for(int i = 0; i < inputString.length(); i++) {
                if(inputString.charAt(i) == '0') {
                    currentState = stateDiagram[currentState][0];
                }
                else if(inputString.charAt(i) == '1') {
                    currentState = stateDiagram[currentState][1];
                }
            }
            // Final state checks
            if(currentState == acceptingState) {
                System.out.println("Divisible by n (in this case 3)");
            }
            else {
                System.out.println("Not divisible by n (in this case 3)");
            }
            // Prompt for another string
            inputString = scnr.nextLine();
        }
    }

    /* Other way to do it is by using switch statements. This is not preferred by Dutter.
       It's literally just switching on the string then switching on the individual characters, it kinda sucks.
       This method is in the book if I really want it.
     */
}
