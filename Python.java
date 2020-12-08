import java.io.*;
import java.util.*;

public class Python {

    static Hashtable<String, String> vars_table = new Hashtable<String, String>();

    static void makeVar(String line) {
        StringTokenizer tokens = new StringTokenizer(line);
        String varName = tokens.nextToken();
        String operator = tokens.nextToken();
        String value = tokens.nextToken();

        if (checkVar(value)) {
            value = vars_table.get(value);
        }

        if (operator.equals("+=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current + val);
            vars_table.put(varName, result);
        }
        if (operator.equals("-=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current - val);
            vars_table.put(varName, result);
        }
        if (operator.equals("*=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current * val);
            vars_table.put(varName, result);
        }
        if (operator.equals("/=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current / val);
            vars_table.put(varName, result);
        }
        if (operator.equals("^=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current ^ val);
            vars_table.put(varName, result);
        }
        if (operator.equals("%=") && checkVar(varName)) {
            int current = Integer.parseInt(vars_table.get(varName));
            int val = Integer.parseInt(value);
            String result = String.valueOf(current % val);
            vars_table.put(varName, result);
        }
        if (operator.equals("=") && checkVar(varName)) {
            vars_table.replace(varName, value);
        }
        if (operator.equals("=") && !checkVar(varName)) {
            vars_table.put(varName, value);
        }
        System.out.println("End of makeVar");
        System.out.println(vars_table);
    }

    static boolean checkVar(String varName) {
        if (vars_table.containsKey(varName)) {
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

        if (conditional.contains(op)) {
            return "conditional";
        }
        if (assignment.contains(op)) {
            return "assignment";
        }
        if (arithmetic.contains(op)) {
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
        if (funct.equals("if")) {
            System.out.println(funct);
            System.out.println(tokens.nextToken());
        }
        if (funct.equals("print")) {
            pyprint(line);
            line = br.readLine();
            classifyLine(line, br);
        }
        if (funct.equals("while")) {
            System.out.println(funct);
            System.out.println(tokens.nextToken());
        }
        if (funct.equals("for")) {
            System.out.println(funct);
            System.out.println(tokens.nextToken());
        } else {
            System.out.println(funct);
        }
    }

    static boolean checkCond(String first, String operator, String second) {
        int leftInt = 0;
        int rightInt = -1;
        String leftStr = "";
        String rightStr = "";

        int lisInt = 0;
        int risInt = 0;

        if (checkVar(first)) { // check if the first
            System.out.println("Var exists");
            if (isNumeric(vars_table.get(first))) {
                System.out.println("Var is num");
                leftInt = Integer.parseInt(vars_table.get(first));
                lisInt = 1;
            } else {
                leftStr = vars_table.get(first);
                lisInt = 0;
            }
        }
        if (isNumeric(first) && !checkVar(first)) {
            leftInt = Integer.parseInt(first);
            lisInt = 1;
        } else {
            leftStr = first;
            lisInt = 0;
        }

        if (checkVar(second)) {
            if (isNumeric(vars_table.get(second))) {
                rightInt = Integer.parseInt(vars_table.get(second));
                risInt = 1;
            } else {
                rightStr = vars_table.get(second);
                risInt = 0;
            }
        }
        if (isNumeric(second) && !checkVar(second)) {
            rightInt = Integer.parseInt(second);
            risInt = 1;
        } else {
            rightStr = second;
            risInt = 0;
        }

        if (lisInt == 1 && risInt == 1) {
            if (operator.equals("==")) {
                if (leftInt == rightInt) {
                    return true;
                } else {
                    return false;
                }
            }
            if (operator.equals("<=")) {
                if (leftInt > rightInt) {
                    return true;
                } else {
                    return false;
                }
            }
            if (operator.equals(">=")) {
                if (leftInt < rightInt) {
                    return true;
                } else {
                    return false;
                }
            }
            if (operator.equals("<")) {
                if (leftInt < rightInt) {
                    return true;
                } else {
                    return false;
                }
            }
            if (operator.equals(">")) {
                if (leftInt > rightInt) {
                    return true;
                } else {
                    return false;
                }
            }
            if (operator.equals("!=")) {
                if (leftInt != rightInt) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        if (lisInt == 0 && risInt == 0) {
            if (operator.equals("==")) {
                if (leftStr == rightStr) {
                    return true;
                } else {
                    return false;
                }
            }
            if (operator.equals("!=")) {
                if (leftInt != rightInt) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    static void ifLoop(String line, BufferedReader br) throws IOException {
        StringTokenizer tokens = new StringTokenizer(line);
        String call = tokens.nextToken();
        String first = tokens.nextToken();
        String operator = tokens.nextToken();
        String second = tokens.nextToken().replaceAll(":", "");
        int loopTab = line.indexOf(line.trim());
        
        String nextLine = br.readLine();
        int nextTab = line.indexOf(line.trim());
                while( (nextTab-loopTab) == 4) {
                    lines.add(nextLine);
                    nextLine = br.readLine();
                }

        if (checkCond(first, operator, second)) {
            for( String line : lines) {
                classifyLine(nextLine, br);
                nextLine = br.readLine();
            }
        } else {
            String nextLine = br.readLine();
            classifyLine(nextLine, br);
        }
    }
    
    static void whileLoop(String line, BufferedReader br) throws IOException {
        List<String> lines = new ArrayList();
        int loopTab = line.indexOf(line.trim());
        
        if(line.contains("(")) {
            StringTokenizer splitCall = new StringTokenizer(line, "()");
            String call = splitCall.nextToken();
            String inside = splitCall.nextToken();
            
            StringTokenizer condition = new StringTokenizer(inside);
            String left = condition.nextToken();
            String operator = condition.nextToken();
            String right = condition.nextToken();
            
            String nextLine = br.readline();
            int nextTab = line.indexOf(line.trim());
            while( (nextTab-loopTab) == 4) {
                lines.add(nextLine);
                nextLine = br.readLine();
            }
            
            while(checkCond(left, operator, right)) {
            for(String line : lines) {
                classifyLine(line);   
            }
        }
            
        } else {
            StringTokenizer splitCall = new StringTokenizer(line);
            String call = splitCall.nextToken();
            String left = splitCall.nextToken();
            String operator =  splitCall.nextToken();
            String right = splitCall.nextToken();
            
            if(splitCall.hasMoreTokens()) {
                String condition = splitCall.nextToken();
                String eLeft = splitCall.nextToken();
                String eOperator = splitCall.nextToken();
                String eRight = splitCall.nextToken();
                String nextLine = br.readline();
                
                int nextTab = line.indexOf(line.trim());
                while( (nextTab-loopTab) == 4) {
                    lines.add(nextLine);
                    nextLine = br.readLine();
                }
                
                if(condition.equals("AND") {
                    while(checkCond(left, operator, right) && checkCond(eleft, eoperator, eright)) {
                        for(String line : lines) {
                            classifyLine(line)   
                        }
                    }
                } if(condition.equals("OR") {
                    while(checkCond(left, operator, right) || checkCond(eLeft, eOperator, eRight)) {
                        for(String line : lines) {
                            classifyLine(line)   
                        }
                }
            } else {
                int nextTab = line.indexOf(line.trim());
                while( (nextTab-loopTab) == 4) {
                    lines.add(nextLine);
                    nextLine = br.readLine();
                }    
                    
                while(checkCond(left, operator, right)) {
                    for(String line : lines) {
                        classifyLine(line)   
                    }
                }
            }
        }
    }
    
    static void forLoop(String line, BufferedReader br) throws IOException {
        
    }
    
    static void pyPrint(String line) {
        StringTokenizer splitCall = new StringTokenizer(line, "()");
        String call = splitCall.nextToken();
        String inside = splitCall.nextToken();
        
        List<String> content = new ArrayList();
        StringTokenizer splitContent = new StringTokenizer(inside, "+");
        while( splitContent.hasMoreTokens() ){
            String tok = splitContent.nextToken();
            tok.replaceAll("str(","");
            tok.replaceAll(")","");
            if(checkVar(tok)){
                content.add(vars_table.get(tok);   
            }else {
                content.add(tok);
            }
        }
        
        String output = new String();
        for(String temp : content) {
            output.concat(temp);
        }
        
        System.out.println(output);
    }

    static void classifyLine(String line, BufferedReader br) throws IOException {
        StringTokenizer token = new StringTokenizer(line);
        String first = token.nextToken();
        if( first.equals("if") ) {
            ifLoop(line, br);
        } if(first.equals("while") {
            whileLoop(line, br);  
        } if(first.equals("for") {
            forLoop(line, br);
        } if( line.contains("(") ) {
            function(line, br);
        } if( checkOp(line).equals("assignment") && line.indexOf(line.trim()) > 0 ) {
            makeVar(line);
        } if( first.contains("#") ) {
            System.out.println("Comment" + line);
        } if(line .isEmpty()) {
               System.out.println("Empty");
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
