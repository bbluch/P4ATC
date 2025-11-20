/**
 * Represents a node in the Skip List.
 * * @author benblucher, austink23
 * @version Nov 16, 2025
 */
public class SkipNode<K extends Comparable<K>> {
    private KVPair<K> rec;
    private SkipNode<K>[] forward;

    // ----------------------------------------------------------
    /**
     * Get the element (value) stored in the node.
     * @return The element (AirObject)
     */
    public Object element() {
        return rec.value();
    }

    // ----------------------------------------------------------
    /**
     * Get the key stored in the node.
     * @return The key (AirObject name)
     */
    public K key() {
        return rec.key();
    }

    // ----------------------------------------------------------
    /**
     * Constructor for a SkipNode.
     * @param key The comparable key (e.g., AirObject name)
     * @param elem The element/value (e.g., AirObject)
     * @param level The level of the node
     */
    public SkipNode(K key, Object elem, int level) {
        rec = new KVPair<K>(key, elem);
        // forward array has size level + 1 (for levels 0 to level)
        forward = new SkipNode[level + 1];
        for (int i = 0; i <= level; i++) { // Corrected loop: level is max index
            forward[i] = null;
        }
    }

    // ----------------------------------------------------------
    /**
     * Get the forward array.
     * @return The forward array of SkipNode pointers.
     */
    public SkipNode[] getForward() {
        return forward;
    }

    // ----------------------------------------------------------
    /**
     * Converts the node's record to its string representation.
     * * @return The String representation of the record.
     */
    @Override
    public String toString() {
        // This likely needs to return the AirObject's toString() value
        return rec.toString();
    }
}