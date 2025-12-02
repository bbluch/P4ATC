// -------------------------------------------------------------------------
/**
 * Interface for Bintree nodes (Composite Pattern).
 * 
 * @author benblucher
 * 
 * @author austink23
 * @version Nov 20, 2025
 */
public interface BinNode {

    // ... existing insert/delete/isLeaf/countNodes signatures ...

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param obj
     * @param x
     * @param y
     * @param z
     * @param size
     * @param level
     * @return
     */
    BinNode insert(AirObject obj, int x, int y, int z, int size, int level);


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param obj
     * @param x
     * @param y
     * @param z
     * @param size
     * @param level
     * @return
     */
    BinNode delete(AirObject obj, int x, int y, int z, int size, int level);


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    boolean isLeaf();


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
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
     * @param size
     *            Node size
     * @param level
     *            Node level
     */
    void collisions(StringBuilder sb, int x, int y, int z, int size, int level);


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
     * @param size
     *            Node size
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
        int size,
        int level);
}
