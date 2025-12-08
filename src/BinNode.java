// -------------------------------------------------------------------------
/**
 * Interface for Bintree nodes (Composite Pattern).
 * 
 * @author benblucher, austink23
 * 
 * @author austink23
 * @version Nov 20, 2025
 */
public interface BinNode {

    // ... existing insert/delete/isLeaf/countNodes signatures ...

    // ----------------------------------------------------------
    /**
     * Insert method for bintree.
     * 
     * @param obj
     *            object inserted
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     *            Node size
     * @param level
     * @return node inserted
     */
    BinNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level);


    // ----------------------------------------------------------
    /**
     * Deletes a node from the Bintree.
     * 
     * @param obj
     *            object inserted
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     *            Node size
     * @param level
     * @return node deleted
     */
    BinNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level);


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return result of check
     */
    boolean isLeaf();


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return count of nodes
     */
    int countNodes();


    /**
     * Traverse for collisions.
     * 
     * @param sb
     *            StringBuilder for output
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     *            Node size
     * @param level
     *            Node level
     */
    void collisions(
        StringBuilder sb,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level);


    /**
     * Traverse for intersections with a query box.
     * 
     * @param sb
     *            StringBuilder for output
     * @param query
     *            The query box details {x, y, z, w, h, d}
     * @param x
     *            Node x origin
     * @param y
     *            Node y origin
     * @param z
     *            Node z origin
     * @param w
     *            width
     * @param h
     *            height
     * @param d
     *            depth
     * @param level
     *            Node level
     * @return Number of nodes visited
     */
    int intersect(
        StringBuilder sb,
        int[] query,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level);
}
