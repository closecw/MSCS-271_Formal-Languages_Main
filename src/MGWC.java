import java.util.Scanner;

/**
 * Class for the DFA of the Man-Wolf-Goat-Cabbage problem.
 * The goat cannot be left alone with the cabbage, the wolf cannot be left alone with the goat.
 * @author Carter Close
 * @version 1.0
 */
public class MGWC {
    /**
     * Main method, defines everything and provides all logic
     * @param args for CLI
     */
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        /*
            Transform alphabet {n, w, g, c} into integer representation alphabet {0, 1, 2, 3}:
            nothing = 0
            wolf = 1
            goat = 2
            cabbage = 3
        */
        int[][] stateDiagram = {
            //0, 1, 2, 3
            {11, 1, 11, 11}, //State 0, W  mgwc, E: none
            {0, 0, 2, 11}, //State 1, W: mw, E: gc
            {3, 11, 1, 11}, //State 2, W: wc, E: mg
            {2, 9, 11, 4}, //State 3, W: mcw, E: g
            {11, 11, 5, 3}, //State 4, W: w, E: mcg
            {11, 6, 4, 11}, //State 5, W: mgw, E: c
            {7, 5, 11, 11}, //State 6, W: g, E: mwc
            {11, 11, 8, 11}, //State 7, W: mg, E: wc
            {8, 8, 8, 8}, //State 8, W: none, E: mwgc
            {11, 3, 11, 11}, //State 9, (from state 3), W: c, E: mgw
            {11, 11, 9, 6}, //State 10, W: w: mcg, w
            {11, 11, 11, 11}, //State 11 (trap state): Needs to fail everything that doesn't work
        };

        int startState = 0;
        int acceptingState = 7;
        String inputString = "";
        int currentState;

        System.out.println("Man-Wolf-Goat-Cabbage: Enter a string for a moveset (0 is nothing, 1 is wolf, 2 is goat, 3 is cabbage): ");
        inputString = scnr.nextLine();
        while(!inputString.isEmpty()) {
            currentState = startState;
            // Handling each possible int character in the string
            for (int i = 0; i < inputString.length(); i++) {
                if (inputString.charAt(i) == '0') {
                    currentState = stateDiagram[currentState][0];
                } else if (inputString.charAt(i) == '1') {
                    currentState = stateDiagram[currentState][1];
                } else if (inputString.charAt(i) == '2') {
                    currentState = stateDiagram[currentState][2];
                } else if (inputString.charAt(i) == '3') {
                    currentState = stateDiagram[currentState][3];
                }
            }
            // Solution checks
            if(currentState == acceptingState) {
                System.out.println("Solution!");
            }
            else {
                System.out.println("Not a solution");
            }
            inputString = scnr.nextLine();
        }

    }
}
