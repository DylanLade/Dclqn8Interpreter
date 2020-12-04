import java.io.*;
import java.util.*;

public class Python {

    Hashtable<String, String> vars = new Hashtable<String, String>();

    static void makeVar(String line) {
        
    } 

    static boolean checkVar(String line) {

    }

    static boolean checkOp(String op) {

        List<String> conditional = new ArrayList<>(List.of("==", "<=", ">=", "<", ">", "!="));
        List<String> assignment = new ArrayList<>(List.of("=", "+=", "-=", "*=", "/=", "^=", "%="));
        List<String> arithmetic = new ArrayList<>(List.of("+", "-", "*", "/", "%", "^"));

        if(conditional.contains(op)) {
            return true;
        } if(assignment.contains(op)) {
            return true;
        } if(arithmetic.contains(op)) {
            return true;
        } else {
            return false;
        }
    }

    static void function(String line) {
        StringTokenizer tokens = new StringTokenizer(line, "()");
        String funct = tokens.nextToken();
        if(funct.equals("if")) {
            System.out.println(funct);
            System.out.println(tokens.nextToken());
        } if(funct.equals("print")) {
            System.out.println(funct);
            System.out.println(tokens.nextToken());
        } if(funct.equals("while")) {
            System.out.println(funct);
            System.out.println(tokens.nextToken());
        } if(funct.equals("for")) {
            System.out.println(funct);
            System.out.println(tokens.nextToken());
        } else {
            System.out.println(funct);
        }
    }

    static void classifyLine(String line) {
        StringTokenizer tokens = new StringTokenizer(line);
        String token = tokens.nextToken();
        if( token.contains("(")) {
            function(line);
        } if( checkOp(token) ) {
            System.out.println("operator");
        } if(token.contains("\"")) {
            System.out.println("String");
        } if(token.contains("#")) {
            System.out.println("Comment");
        } else {
            System.out.println(token);
        }
    }
    
    public static void main(String args[]) {
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            while (line != null) {
                StringTokenizer tokens = new StringTokenizer(line);
                while(tokens.hasMoreTokens()) {
                    String current = tokens.nextToken();
                    // System.out.println(current);
                    classifyLine(line);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}