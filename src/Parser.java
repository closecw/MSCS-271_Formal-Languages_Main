import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Stack;

/**
 * Class for an LL(1) parser of in-fix mathematical expressions
 * Code referenced from Dr. Seth Dutter, translated by me
 * @author Carter Close
 * @version 1.0
 */
public class Parser {
    /**
     * Enums for every possible string stack character
     * Makes referencing the table a lot easier
     */
    enum tokens {
        PLUS_TOKEN(0),
        MINUS_TOKEN(1),
        MULT_TOKEN(2),
        DIV_TOKEN(3),
        LP_TOKEN(4),
        RP_TOKEN(5),
        DOUBLE_TOKEN(6),
        EOF_TOKEN(7),
        T_NT(0),
        t_NT(1),
        R_NT(2),
        r_NT(3),
        V_NT(4);

        private final int value;
        tokens(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        String[][] ll_table = {
            //  +      -     *       /     (     )  double  $
            {""    ,""    ,""    ,""    ,"Rt"  ,""  ,"Rt" ,"" },  // T->
            {"+Rt" ,"-Rt" ,""    ,""    ,""    ,"e" ,""   ,"e"},  // t->
            {""    ,""    ,""    ,""    ,"Vr"  ,""  ,"Vr" ,"" },  // R->
            {"e"   ,"e"   ,"*Vr" ,"/Vr" ,""    ,"e" ,""   ,"e"},  // r->
            {""    ,""    ,""    ,""    ,"(T)" ,""  ,"d"  ,"" }   // V->
        };

        String math_exp;
        Stack<Character> stack_machine = new Stack<>();
        Pattern double_pattern = Pattern.compile("(\\d+\\.?\\d*|\\.\\d+)");     // Regex pattern for doubles

        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter a mathematical expression: ");
        math_exp = scnr.nextLine();

        math_exp = math_exp.replaceAll(double_pattern.pattern(), "d");
        math_exp += "$";

        stack_machine.push('$');
        stack_machine.push('T');

        for(long i = 0; i < math_exp.length(); i++) {
             if(stack_machine.empty()){
                System.out.println("String is not valid");
                System.out.println(math_exp.substring(0, (int) i) + " ^ " + math_exp.substring((int) i));
                return;
             }

             String push_string = "";
             int next_token;
             int current_nonterminal;
             char string_char = math_exp.charAt((int) i);
             Character stack_char = stack_machine.pop();

             switch(string_char) {
                 case '+': next_token = tokens.PLUS_TOKEN.getValue(); break;
                 case '-': next_token = tokens.MINUS_TOKEN.getValue(); break;
                 case '*': next_token = tokens.MULT_TOKEN.getValue(); break;
                 case '/': next_token = tokens.DIV_TOKEN.getValue(); break;
                 case '(': next_token = tokens.LP_TOKEN.getValue(); break;
                 case ')': next_token = tokens.RP_TOKEN.getValue(); break;
                 case 'd': next_token = tokens.DOUBLE_TOKEN.getValue(); break;
                 case '$': next_token = tokens.EOF_TOKEN.getValue(); break;
                 default:
                     System.out.println("Error in input string, unidentified char: " + string_char);
                     System.out.println(math_exp.substring(0, (int) i) + " ^ " + math_exp.substring((int) i));
                     return;
             }

             while(string_char != stack_char) {
                 switch(stack_char) {
                     case 'T': current_nonterminal = tokens.T_NT.getValue(); break;
                     case 't': current_nonterminal = tokens.t_NT.getValue(); break;
                     case 'R': current_nonterminal = tokens.R_NT.getValue(); break;
                     case 'r': current_nonterminal = tokens.r_NT.getValue(); break;
                     case 'V': current_nonterminal = tokens.V_NT.getValue(); break;
                     default:
                         System.out.println("String is not valid");
                         System.out.println(math_exp.substring(0, (int) i) + " ^ " + math_exp.substring((int) i));
                         return;
                 }

                 push_string = ll_table[current_nonterminal][next_token];

                 if(push_string.isEmpty()) {
                     System.out.println("String is not valid");
                     System.out.println(math_exp.substring(0, (int) i) + " ^ " + math_exp.substring((int) i));
                     return;
                 }

                 if(push_string.charAt(0) != 'e') {
                     // Reverse order due to the LIFO structure of a stack
                     for(int j = push_string.length() - 1; j >= 0; j--) {
                         stack_machine.push(push_string.charAt(j));
                     }
                 }

                 if(stack_machine.empty()) {
                     System.out.println("String is not valid");
                     System.out.println(math_exp.substring(0, (int) i) + " ^ " + math_exp.substring((int) i));
                     return;
                 }

                 stack_char = stack_machine.pop();
             }
        }

        // Final checks after looping through string
        if(stack_machine.empty()) {
            System.out.println("String is valid");
        }
        else{
            System.out.println("String is not valid");
        }
    }
}