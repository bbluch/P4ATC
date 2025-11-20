// KVPair.java (You will need to implement this class)
public class KVPair<K extends Comparable<K>> implements Comparable<KVPair<K>> {
    private K key;
    private Object value;

    public KVPair(K key, Object value) {
        this.key = key;
        this.value = value;
    }

    public K key() {
        return key;
    }

    public Object value() {
        return value;
    }

    // Since the SkipList operates on the key, KVPair must implement Comparable
    // based on the key.
    @Override
    public int compareTo(KVPair<K> other) {
        // Assuming keys are comparable strings (AirObject names)
        return this.key.compareTo(other.key);
    }
    
    // As indicated in the sample test case, toString should return the object's toString.
    // However, since SkipNode.toString() calls this, we must check if rec.toString()
    // is intended to print the AirObject's full details. 
    // For now, let's assume it calls the value's toString.
    @Override
    public String toString() {
        return value.toString();
    }
}