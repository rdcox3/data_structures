import java.util.Comparator;

/**
 * Comparator class to order the nodes in the priority queue by frequency,
 * single-character precedence, and alphabetical order.
 */
public class HuffmanComparator implements Comparator<HuffmanNode> {
    @Override
    public int compare(HuffmanNode x, HuffmanNode y) {
        // Compare by frequency first
        int freqComparison = Integer.compare(x.freq, y.freq);
        if (freqComparison != 0) {
            return freqComparison;
        }

        // Single letter groups precedence
        boolean xIsSingle = x.characters.length() == 1;
        boolean yIsSingle = y.characters.length() == 1;
        if (xIsSingle && !yIsSingle) {
            return -1;
        } else if (!xIsSingle && yIsSingle) {
            return 1;
        }

        // Alphabetical order for ties
        return x.characters.compareTo(y.characters);
    }
}

