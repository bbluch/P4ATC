import java.util.Random;

// SkipList.java
// -------------------------------------------------------------------------
/**
 * Implements a Skip List data structure.
 * * @author benblucher, austink23
 * @version Nov 16, 2025
 */
public class SkipList<K extends Comparable<K>> { // Implement Dictionary/appropriate interface
    
    private SkipNode<K> head;
    private int level;
    private int size;
    // ran needs to be static if used this way, as shown in the snippet
    static private Random ran = new Random(); 

    // ----------------------------------------------------------
    /**
     * Initializes a new SkipList.
     */
    public SkipList() {
        head = new SkipNode<K>(null, null, 0);
        level = 0; // The snippet shows -1, but 0 is safer for immediate use. 
                   // Let's stick to 0 for consistency with the loop logic in insert.
        size = 0;
    }

    // ----------------------------------------------------------
    /**
     * Picks a level using a geometric distribution (p=0.5).
     * @return The new level for a node.
     */
    private int randomLevel() {
        int lev = 0;
        // The condition uses ran.nextInt() % 2 == 0 for a 50% chance of lev++
        while (Math.abs(ran.nextInt()) % 2 == 0) { 
            lev++;
        }
        return lev;
    }

    // ----------------------------------------------------------
    /**
     * Adjusts the head node when the new node's level is deeper than the current list level.
     * @param newLevel The new maximum level of the list.
     */
    private void adjustHead(int newLevel) {
        SkipNode<K> temp = head;
        // Create a new head node with a larger forward array
        head = new SkipNode<K>(null, null, newLevel); 
        
        // Copy pointers from the old head to the new head
        for (int i = 0; i <= level; i++) {
            head.getForward()[i] = temp.getForward()[i];
        }
        
        // The new levels must be initialized to null, which the SkipNode constructor does.
        // Update the list's max level
        level = newLevel;
    }

    // ----------------------------------------------------------
    /**
     * Inserts a key-element pair into the skip list.
     * @param key The comparable key for the element.
     * @param elem The element to store (e.g., AirObject).
     */
    public void insert(K key, Object elem) {
        // 1. Determine level and adjust head if necessary
        int newLevel = randomLevel();
        if (newLevel > level) {
            adjustHead(newLevel);
        }

        // 2. Find insertion position and track update pointers
        // update[i] will store the node whose forward[i] pointer needs to be updated.
        SkipNode<K>[] update = new SkipNode[level + 1];
        SkipNode<K> x = head;
        
        // Iterate from the top level down to level 0
        for (int i = level; i >= 0; i--) { 
            // Traverse forward until the next node is null or its key is greater than the key to insert
            while ((x.getForward()[i] != null) && 
                   (x.getForward()[i].key().compareTo(key) < 0)) {
                x = x.getForward()[i];
            }
            // Store the node *before* the insertion point at level i
            update[i] = x; 
        }

        // 3. Check for duplicates (at level 0) - Project requirement likely prohibits duplicates
        // Move x one step forward (x.forward[0] is the potential duplicate)
        x = x.getForward()[0];
        
        // Skip List logic often assumes no duplicates for simplicity; 
        // if duplicates are allowed, the following check is skipped.
        // Given your project tracks AirObjects by Name, it is likely that duplicate names are NOT allowed.
        if (x != null && x.key().compareTo(key) == 0) {
            // Duplicate found, replace or do nothing. For this project, likely skip/fail insert.
            // Assuming no replacement or insertion for duplicates, just return:
            return; 
        }

        // 4. Create new node and splice into the list
        x = new SkipNode<K>(key, elem, newLevel);
        
        // Splice the new node into the list at levels 0 up to newLevel
        for (int i = 0; i <= newLevel; i++) {
            // New node points to what the update node currently points to
            x.getForward()[i] = update[i].getForward()[i]; 
            // Update node points to the new node
            update[i].getForward()[i] = x;
        }

        size++; // Increment dictionary size
    }


    // ----------------------------------------------------------
    /**
     * Finds and returns the element associated with the given key.
     * @param key The comparable key to search for.
     * @return The matching element, or null if not found.
     */
    public Object find(K key) {
        SkipNode<K> x = head; // Dummy header node
        
        // Iterate from the top level down to level 0
        for (int i = level; i >= 0; i--) { 
            // Traverse forward until the next node is null or its key is >= the search key
            while ((x.getForward()[i] != null) && 
                   (x.getForward()[i].key().compareTo(key) < 0)) { 
                x = x.getForward()[i]; // Go one last step
            }
        }
        
        // Move to the actual record node at level 0 (the potential match)
        x = x.getForward()[0]; 
        
        // Check if a match was found
        if ((x != null) && (x.key().compareTo(key) == 0)) {
            return x.element(); // Got it
        } 
        else {
            return null; // It's not there
        }
    }
    
    // You will also need to implement delete, printskiplist, and rangeprint.
    
    // You will need a getter for size to verify if the list is empty for methods like delete.
    public int size() {
        return size;
    }
    
    // You will need a method to get all records in sorted order for printskiplist 
    // and rangeprint. A simple approach is to traverse the level 0 chain.
    // ...
}