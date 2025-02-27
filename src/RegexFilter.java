import java.io.*;
import java.util.regex.*;

/**
 * A Java application to demonstrate the Java package java.util.regex.
 * We take one command-line arguments, which are treated as a regexp and compiled into a Pattern.
 * We then use that Pattern to filter the standard input,
 * echoing to standard output only those lines that match the Pattern.
 * Ditto from the book "Formal Languages: A Practical Introduction" by Adam Brooks Webber
 * @author Carter Close
 * @version 1.0
 */
public class RegexFilter {
    public static void main(String[] args) throws IOException {
        Pattern p = Pattern.compile(args[0]);  // The regexp
        // Standard input, read and echo lines until EOF
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        while (s != null) {
            Matcher m = p.matcher(s);
            if(m.matches()) System.out.println(s);
            s = in.readLine();
        }
    }
}
