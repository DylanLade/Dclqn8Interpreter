import java.io.*;
import java.util.*;

public class Python {

    static Hashtable<String, String> vars_table = new Hashtable<String, String>();
    static int skip = 0;
    static int isif = 0;
    static int iswhile = 0;
    static int isfor = 0;

    static void makeVar(String line) {
        StringTokenizer tokens = new StringTokenizer(line);
        String varName = tokens.nextToken();
        String operator = tokens.nextToken();
        String value = tokens.nextToken();

        if(checkVar(value)) {
            value = vars_table.get(value);
        }

        if (operator.equals("+=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current + val);
            vars_table.put(varName, result);
        } if (operator.equals("-=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current - val);
            vars_table.put(varName, result);
        } if (operator.equals("*=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current * val);
            vars_table.put(varName, result);
        } if (operator.equals("/=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current / val);
            vars_table.put(varName, result);
        } if (operator.equals("^=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current ^ val);
            vars_table.put(varName, result);
        } if (operator.equals("%=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current % val);
            vars_table.put(varName, result);
        } if(operator.equals("=") && checkVar(varName)){
            vars_table.replace(varName, value);
        } if(operator.equals("=") && !checkVar(varName)) {
            vars_table.put(varName, value);
        }
    } 

    static boolean checkVar(String varName) {
        if(vars_table.containsKey(varName)) {
            return true;
        } else {
            return false;
        }
    }

    static String checkOp(String line) {

        List<String> conditional = new ArrayList<>(List.of("==", "<=", ">=", "<", ">", "!="));
        List<String> assignment = new ArrayList<>(List.of("=", "+=", "-=", "*=", "/=", "^=", "%="));
        List<String> arithmetic = new ArrayList<>(List.of("+", "-", "*", "/", "%", "^"));

        StringTokenizer tokens = new StringTokenizer(line);
        tokens.nextToken();
        String op = tokens.nextToken();

        if(conditional.contains(op)) {
            return "conditional";
        } if(assignment.contains(op)) {
            return "assignment";
        } if(arithmetic.contains(op)) {
            return "arithmetic";
        } else {
            return "notOP";
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

    static void ifLoop(String line) {

    }

    static void classifyLine(String line) {
        StringTokenizer tokens = new StringTokenizer(line);
        String first = tokens.nextToken();
        if( first.equals("if") ) {
            ifLoop(line);
        } if( line.contains("(") ) {
            function(line);
        } if( checkOp(line).equals("assignment") ) {
            makeVar(line);
        } if( first.equals("#") ) {
            System.out.println(line);
        } 
    }
    
    public static void main(String args[]) {
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine().trim();
            while (line != null) {
                if(line.isEmpty()) {
                    line = br.readLine().trim();
                    continue;
                }

                while(skip > 0) {
                    line = br.readLine().trim();
                    skip = skip - 1;
                }

                classifyLine(line);
                line = br.readLine().trim();
            }

            System.out.println(vars_table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}