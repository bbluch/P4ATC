// -------------------------------------------------------------------------
/**
 * Flyweight Empty Node for Bintree.
 * 
 * @author benblucher
 * 
 * @author austink23
 * @version Nov 20, 2025
 */
public class BinEmpty implements BinNode {

    private static BinEmpty instance = new BinEmpty();

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * @return
     */
    public static BinEmpty getInstance() {
        return instance;
    }


    private BinEmpty() {
    }

    // ... insert/delete/isLeaf/countNodes implementation from previous turn ...


    @Override
    public BinNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int size,
        int level) {
        BinLeaf leaf = new BinLeaf();
        return leaf.insert(obj, x, y, z, size, level);
    }


    @Override
    public BinNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int size,
        int level) {
        return this;
    }


    @Override
    public boolean isLeaf() {
        return true;
    }


    @Override
    public int countNodes() {
        return 1;
    }

    // --- New Methods ---


    @Override
    public void collisions(
        StringBuilder sb,
        int x,
        int y,
        int z,
        int size,
        int level) {
        // Empty nodes have no objects, so no collisions.
    }


    @Override
    public int intersect(
        StringBuilder sb,
        int[] query,
        int x,
        int y,
        int z,
        int size,
        int level) {

        // Check if this node intersects the query box
        // Query: 0=x, 1=y, 2=z, 3=xw, 4=yw, 5=zw
        if (!intersects(x, y, z, size, size, size, query[0], query[1], query[2],
            query[3], query[4], query[5])) {
            return 0; // Prune: Does not intersect query box
        }

        // Visited
        sb.append("E (").append(x).append(", ").append(y).append(", ").append(z)
            .append(", ").append(size).append(", ").append(size).append(", ")
            .append(size).append(") ").append(level).append("\n");

        return 1;
    }


    // Helper to check box-box intersection (Node vs Query)
    private boolean intersects(
        int x1,
        int y1,
        int z1,
        int w1,
        int h1,
        int d1,
        int x2,
        int y2,
        int z2,
        int w2,
        int h2,
        int d2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2
            && z1 < z2 + d2 && z1 + d1 > z2;
    }
}
