import java.io.*;
import java.util.*;

/**
 * Main class for Huffman Coding implementation.
 */
public class HuffmanCoding {

    /**
     * Builds the Huffman Tree from the given frequency map.
     * @param freqMap A map of characters and their frequencies.
     * @return The root node of the Huffman Tree.
     */
    public static HuffmanNode buildHuffmanTree(Map<String, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(new HuffmanComparator());

        // Create a leaf node for each character and add it to the priority queue.
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getValue(), entry.getKey()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode combined = new HuffmanNode(left.freq + right.freq, left.characters + right.characters);
            combined.left = left;
            combined.right = right;
            pq.add(combined);
        }

        return pq.poll(); // Return the root of the Huffman Tree
    }

    /**
     * Generates Huffman codes by traversing the Huffman Tree.
     * @param root The root of the Huffman Tree.
     * @param prefix The current Huffman code as a string.
     * @param codeMap A map to store the Huffman codes for each character.
     * @return The map containing Huffman codes for each character.
     */
    public static Map<String, String> generateCodes(HuffmanNode root, String prefix, Map<String, String> codeMap) {
        if (root != null) {
            // Leaf node
            if (root.left == null && root.right == null) {
                codeMap.put(root.characters, prefix);
            } else {
                generateCodes(root.left, prefix + "0", codeMap);
                generateCodes(root.right, prefix + "1", codeMap);
            }
        }
        return codeMap;
    }

/**
 * Encodes the given text using the Huffman codes.
 * @param text The text to encode.
 * @param codeMap A map of Huffman codes for each character.
 * @return The encoded text as a binary string.
 */
public static String encodeText(String text, Map<String, String> codeMap) {
    StringBuilder encodedText = new StringBuilder();
    text = text.toUpperCase(); // Convert clear text to uppercase for uniform encoding

    for (char c : text.toCharArray()) {
        String str = String.valueOf(c);
        if (codeMap.containsKey(str)) {
            encodedText.append(codeMap.get(str));
        } else {
            // Handle or skip characters not in the frequency table
            System.out.println("Character not in frequency table, skipping: " + c);
        }
    }
    System.out.println("\nEncoded Text (Debug): " + encodedText.toString());
    return encodedText.toString();
}

/**
 * Decodes the encoded text using the Huffman Tree.
 * @param encodedText The encoded text as a binary string.
 * @param root The root of the Huffman Tree.
 * @return The decoded text.
 */
public static String decodeText(String encodedText, HuffmanNode root) {
    StringBuilder decodedText = new StringBuilder();
    HuffmanNode current = root;

    for (int i = 0; i < encodedText.length(); i++) {
        char bit = encodedText.charAt(i);
        
        if (bit == '0' || bit == '1') {
            current = (bit == '0') ? current.left : current.right;

            // Leaf node
            if (current.left == null && current.right == null) {
                decodedText.append(current.characters);
                current = root;
            }
        } else {
            // Handle unexpected characters in the encoded text (shouldn't happen with binary encoding)
            System.out.println("Unexpected character in encoded text, skipping: " + bit);
        }
    }
    System.out.println("\nDecoded Text (Debug): " + decodedText.toString());
    return decodedText.toString();
}

    /**
     * Prints the Huffman Tree using preorder traversal.
     * @param root The root of the Huffman Tree.
     */
    public static void printPreorder(HuffmanNode root) {
        if (root == null) return;

        if (root.characters.length() == 1) {
            System.out.println(root.characters + ": " + root.freq);
        } else {
            System.out.println("Internal Node (Freq: " + root.freq + ")");
        }

        printPreorder(root.left);
        printPreorder(root.right);
    }

    /**
     * Main method to read input files, encode and decode text using Huffman coding.
     * @param args Command-line arguments (not used).
     * @throws IOException If an I/O error occurs.
     */
    public static void main(String[] args) throws IOException {
        // Step 1: Read the frequency table from file
        Map<String, Integer> freqMap = new HashMap<>();
        BufferedReader freqReader = new BufferedReader(new FileReader("FreqTable.txt"));
        String line;
        while ((line = freqReader.readLine()) != null) {
            String[] parts = line.split(" - ");
            freqMap.put(parts[0], Integer.parseInt(parts[1]));
        }
        freqReader.close();

        // Step 2: Build the Huffman Tree
        HuffmanNode root = buildHuffmanTree(freqMap);

        // Step 3: Generate Huffman Codes
        Map<String, String> codeMap = new HashMap<>();
        generateCodes(root, "", codeMap);

        // Step 4: Print the Huffman Tree in preorder traversal
        System.out.println("Huffman Tree in Preorder:");
        printPreorder(root);

        // Step 5: Print the Huffman Codes
        System.out.println("\nHuffman Codes:");
        for (Map.Entry<String, String> entry : codeMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // Step 6: Read the clear text from file and encode it
        BufferedReader clearTextReader = new BufferedReader(new FileReader("ClearText.txt"));
        StringBuilder clearText = new StringBuilder();
        while ((line = clearTextReader.readLine()) != null) {
            clearText.append(line).append("\n");
        }
        clearTextReader.close();

        // Remove the last newline character (if added)
        if (clearText.length() > 0) {
            clearText.deleteCharAt(clearText.length() - 1);
        }

        System.out.println("\nClear Text Read from File:\n" + clearText.toString());
        String encodedText = encodeText(clearText.toString(), codeMap);

        // Print the encoded text
        System.out.println("\nEncoded Text: " + encodedText);

        // Step 7: Decode the encoded text from file
        BufferedReader encodedTextReader = new BufferedReader(new FileReader("Encoded.txt"));
        while ((line = encodedTextReader.readLine()) != null) {
            String decodedText = decodeText(line, root);
            System.out.println("Decoded Text: " + decodedText);
        }
        encodedTextReader.close();
    }
}
