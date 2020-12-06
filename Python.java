import java.io.*;
import java.util.*;

public class Python {

    static Hashtable<String, String> vars_table = new Hashtable<String, String>();

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
        System.out.println("End of makeVar");
        System.out.println(vars_table);
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

    static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
            System.out.println(i);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    static void function(String line, BufferedReader br) {
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

    static boolean checkCond(String first, String operator, String second) {
        Object left;
        Object right;
        
        if(checkVar(first)) { // check if the first 
            System.out.println("Var exists");
            if(isNumeric(vars_table.get(first))) {
                System.out.println("Var is num");
                left = Integer.parseInt(vars_table.get(first));
            } else {
                left = vars_table.get(first);
            }
        } if(isNumeric(first) && !checkVar(first)) {
            left = Integer.parseInt(first);
        } else {
            left = first;
        } 

        if(checkVar(second)) {
            if(isNumeric(vars_table.get(second))) {
                right = Integer.parseInt(vars_table.get(second));
            } else {
                right = vars_table.get(second);
            }
        } if(isNumeric(second) && !checkVar(second)) {
            right = Integer.parseInt(second);
        } else {
            right = second;
        }

        System.out.println(left);
        System.out.println(right);

        if (operator.equals("==")) {
            if(left == right) {
                return true;
            } else {
                return false;
            }
        } if(operator.equals("<=")){
            if((int) left > (int) right) {
                return true;
            } else {
                return false;
            }
        } if(operator.equals(">=")){
            if((int) left < (int) right) {
                return true;
            } else {
                return false;
            }
        } if(operator.equals("<")){
            if((int)left < (int)right) {
                return true;
            } else {
                return false;
            }
        } if(operator.equals(">")){
            if((int)left > (int)right) {
                return true;
            } else {
                return false;
            }
        } if(operator.equals("!=")){
            if(left != right) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    static void ifLoop(String line, BufferedReader br) {
        StringTokenizer tokens = new StringTokenizer(line);
        String call = tokens.nextToken();
        String first = tokens.nextToken();
        String operator = tokens.nextToken();
        String second = tokens.nextToken().replaceAll(":","");

        if(checkCond(first, operator,second)) {
            System.out.println("If is true");
        } else {
            System.out.println("If is false");
        }
    }

    static void classifyLine(String line, BufferedReader br) {
        StringTokenizer token = new StringTokenizer(line);
        String first = token.nextToken();
        if( first.equals("if") ) {
            ifLoop(line, br);
        } if( line.contains("(") ) {
            function(line, br);
        } if( checkOp(line).equals("assignment") && !line.contains("\t") || !line.contains("    ")) {
            makeVar(line);
        } if( first.equals("#") ) {
            System.out.println(line);
        } 
    }
    
    public static void main(String args[]) {
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            while (line != null) {
                if(line.isEmpty()) {
                    line = br.readLine();
                    continue;
                }
                System.out.println(line);
                classifyLine(line, br);
                line = br.readLine();
            }

            System.out.println(vars_table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}