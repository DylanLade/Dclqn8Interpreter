# Dclqn8Interpreter
Python Interpreter project for CS4450

## Project Details
This project is an interpreter for low level functions of the python language. This interpreter is built in java and implements if, else, while, for, and print. It also supports varriable assigning and updating through assignment and arithmetic operators. Support for conditional operators is also included of this project. To handle my input for this interpreter I chose to use a buffered reader to read the lines from the file, if there was a situation where I would need to check the tabbing I would use the index of the .trim() function to get the number of white spaces from the front of the new line and compare it to the number of the previous to check if there is one tab separating the two. For if/else blocks I would move through the file with the buffered reader and not worry about previous lines, but for while and for loops I load the line that are inside the loop into a String list and use that to keep me from losing the previous lines of code if he loop needs to execute more than once. If the line contains a '#' (comment line) I just simply print the line to the console running the application, but this can just as easily be removed to keep the true nature of comments. For Print I split the line by '(' and ')' to get the function syntax out of the way then I split by '+' to check if there are any varriables I need to instert into the string, after that I extract the variables and build the final string using the remaining tokens to output to console. When assigning and checking varriables I chose to store everything as a string into a hashtable that had the key as the varriable name and the value was the string of what was being assigned. I would check if the varriable exists for both sides of the '=' and from there depending on the operator I would do the given operation. To find if the value was a number I would use .parseInt() from the Integer class to take the numer saved as a String and change it to the correct type.

## How to run
I was running this whole program from the command line. 
#### First
`javac Python.java`
#### After the program builds enter and python file you would like to check
`java Python "test.py"

