import java.io.*;

/**
 * This class contains the main program to convert prefix expressions to postfix expressions.
 * It reads input from a file, processes each line, and writes the output to another file.
 */
public class PrefixToPostfix {

    /**
     * Checks if a character is a valid operator.
     * @param c The character to check.
     * @return True if the character is an operator, otherwise false.
     */
    public static boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '$');
    }

    /**
     * Converts a prefix expression to a postfix expression using a stack.
     * This method processes the expression character by character in reverse order.
     * @param prefix The prefix expression to be converted.
     * @return The corresponding postfix expression or an error message if the input is invalid.
     */
    public static String convert(String prefix) {
        Stack stack = new Stack(prefix.length());
        System.out.println("Converting: " + prefix);  // Debugging line

        // Read prefix in reverse order, character by character
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            System.out.println("Processing: " + c);  // Debugging line

            if (isOperator(c)) {
                // Ensure there are at least two operands for each operator
                if (stack.size() < 2) {
                    return "Error: Invalid Prefix Expression";
                }
                String operand1 = stack.pop();
                String operand2 = stack.pop();

                // Combine operands and operator in postfix form
                String temp = operand1 + operand2 + c;
                stack.push(temp);
            } else if (Character.isLetter(c)) {
                // Push operand to stack
                stack.push(String.valueOf(c));
            } else if (c != ' ') {
                // Handle invalid characters
                return "Error: Invalid Character in Expression: " + c;
            }
        }

        // Check for valid final stack size
        if (stack.size() != 1) {
            return "Error: Invalid Prefix Expression";
        }

        return stack.pop();
    }

    /**
     * The main method to read input from a file, convert each prefix expression to postfix,
     * and write the results to an output file.
     * @param args Command line arguments for input and output file names.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java PrefixToPostfix <inputFileName> <outputFileName>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        try (BufferedReader br = new BufferedReader(new FileReader(inputFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Process each line from the input file
                String postfix = convert(line.trim());
                System.out.println("Prefix: " + line.trim() + " -> Postfix: " + postfix);  // Debugging line
                // Write the converted expression to the output file
                bw.write(postfix);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}