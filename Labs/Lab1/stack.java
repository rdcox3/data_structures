/**
 * This class implements a basic stack data structure.
 * It uses an array to store stack elements and provides
 * standard stack operations such as push, pop, peek, and size.
 */
public class Stack {
    private int maxSize;
    private String[] stackArray;
    private int top;
    /**
     * Constructor to initialize the stack with a specified size.
     * @param size Maximum size of the stack.
     */
    public Stack(int size) {
        maxSize = size;
        stackArray = new String[maxSize];
        top = -1;
    }

    /**
     * Pushes a value onto the stack.
     * @param value The value to be pushed onto the stack.
     */
    public void push(String value) {
        stackArray[++top] = value;
    }

    /**
     * Pops a value from the stack.
     * @return The value popped from the stack.
     */
    public String pop() {
        return stackArray[top--];
    }

    /**
     * Peeks at the top value of the stack without removing it.
     * @return The top value of the stack.
     */
    public String peek() {
        return stackArray[top];
    }

    /**
     * Checks if the stack is empty.
     * @return True if the stack is empty, otherwise false.
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Returns the current size of the stack.
     * @return The number of elements in the stack.
     */
    public int size() {
        return top + 1;
    }
}
