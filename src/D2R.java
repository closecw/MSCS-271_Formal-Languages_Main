/**
 * Class for the d2r algorithm on the val(x) % 7 == 0 NFA
 * Turns an NFA into a regular expression via recursive methods
 * Original Python code provided by Seth Dutter, rewritten in Java by me
 * @author Carter Close
 * @version 1.0
 */
public class D2R {
    static int[] alphabet = {0, 1};
    static int[][] stateDiagram = {
            {0, 1},
            {2, 3},
            {4, 5},
            {6, 0},
            {1, 2},
            {3, 4},
            {5, 6},
    };
    static int[] acceptingStates = {0};

    /**
     * Recursive method for the algorithm
     * @param M the state diagram above
     * @param i the state to start in
     * @param j the state to end in
     * @param k the string cannot pass through any states less than this parameter
     * @return either some combination of strings, or the d2r method again (the recursive part)
     */
    public static String d2r(int[][] M, int i, int j, int k) {
        if(k >= M.length) {
            String r = "";
            for(int letter : alphabet) {
                if(M[i][letter] == j) {
                    if(!r.isEmpty()) {
                        r += "+";
                    }
                    r += letter;
                }
            }
            if(r.isEmpty()) {
                r = "{}";
            }
            if(i == j) {
                return r + "+e";
            }
            else {
                return r;
            }
        }
        else {
            return d2r(M, i, j, k + 1) + "+(" + d2r(M, i, k, k+1) + ")(" + d2r(M, k, k, k+1) + ")*(" + d2r(M, k, j, k+1) + ")";
        }
    }

    /**
     * Main method, defines the string and calls {@link #d2r} method
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        String regex = "";
        for(int state : acceptingStates) {
            if(regex.isEmpty()) {
                regex = d2r(stateDiagram, 0, state, 0);
            }
            else {
                regex += "+" + d2r(stateDiagram, 0, state, 0);
            }
        }
        System.out.println(regex);
    }
}
