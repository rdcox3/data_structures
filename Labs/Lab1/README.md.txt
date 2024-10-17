README
Project: Prefix to Postfix Conversion
Author: Ron Cox
Java Version
* Java SE Development Kit (JDK): 17
IDE Used
* IDE: IntelliJ IDEA 2023.1
Files Included
* Stack.java: Implementation of the stack data structure.
* PrefixToPostfix.java: Main program for converting prefix expressions to postfix expressions.
* RequiredInput.txt: Sample input file containing prefix expressions.
* ConvertedOutput.txt: Output file where the converted postfix expressions will be written.
How to Compile and Run the Program
1. Ensure all files are in the same directory:
   * Stack.java
   * PrefixToPostfix.java
   * RequiredInput.txt
2. Open a terminal or command prompt.
3. Navigate to the directory containing the files.
4. Compile the Java files: 
javac Stack.java PrefixToPostfix.java
	5. Run the program with the required input and output file names as command line arguments:
java PrefixToPostfix RequiredInput.txt ConvertedOutput.txt
Input File Format
* The input file (RequiredInput.txt) should contain one prefix expression per line.
* Ensure that the expressions contain only single letter operands and the operators +, -, *, /, $.
Output
* The program will read the prefix expressions from the input file, convert them to postfix expressions, and write the results to the output file (ConvertedOutput.txt).
Error Handling
* The program includes error handling for invalid characters, insufficient operands, and invalid expressions. Appropriate error messages will be written to the output file.
Additional Notes
* Ensure the input file is correctly formatted and located in the same directory as the compiled Java files.
* The command line arguments for input and output file names are mandatory for the program to run correctly.




javac Stack.java PrefixToPostfix.java