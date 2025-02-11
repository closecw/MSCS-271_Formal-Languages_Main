import java.util.Scanner;

/**
 * Class for finding if a binary string's length mod 7 = 2
 * @author Carter Close
 * @version 1.0
 */
public class Mod7 {
    /**
     * Main method, defines everything and provides all logic
     * @param args for CLI
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        // Make transition function delta a LUT/2D array
        int[][] stateDiagram = {
                //0, 1
                {0, 1}, //state 0
                {2, 3}, //state 1
                {4, 5}, //state 2
                {6, 0}, //state 3
                {1, 2}, //state 4
                {3, 4}, //state 5
                {5, 6}, //state 6
        };

        int startState = 0;
        int acceptingState = 2;
        String inputString;
        int currentState;

        // Input handling
        System.out.println("Enter a binary string:");
        inputString = scnr.nextLine();
        // While loop keeps the program asking for a string until it receives an empty string (just the enter key)
        while(!inputString.isEmpty()) {
            currentState = startState;
            // Loop over each element
            for(int i = 0; i < inputString.length(); i++) {
                if(inputString.charAt(i) == '0') {
                    currentState = stateDiagram[currentState][0];
                }
                else if(inputString.charAt(i) == '1') {
                    currentState = stateDiagram[currentState][1];
                }
            }
            // Solution Checks
            if(currentState == acceptingState) {
                System.out.println("Accepted: |x|mod 7 == 2");
            }
            else {
                System.out.println("Rejected: |x|mod 7 != 2");
            }
            // Prompt for another string
            inputString = scnr.nextLine();
        }
    }
}
