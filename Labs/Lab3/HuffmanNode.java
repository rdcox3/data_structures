/**
 * Class representing a node in the Huffman Tree.
 */
public class HuffmanNode {
    int freq;
    String characters;
    HuffmanNode left, right;

    /**
     * Constructor to create a HuffmanNode.
     * @param freq Frequency of the characters.
     * @param characters String of characters stored in the node.
     */
    public HuffmanNode(int freq, String characters) {
        this.freq = freq;
        this.characters = characters;
        this.left = null;
        this.right = null;
    }
}

