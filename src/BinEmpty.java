// -------------------------------------------------------------------------
/**
 * Flyweight Empty Node for Bintree.
 * * @author benblucher
 * 
 * @author austink23
 * @version Nov 20, 2025
 */
public class BinEmpty implements BinNode {

    private static BinEmpty instance = new BinEmpty();

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public static BinEmpty getInstance() {
        return instance;
    }


    private BinEmpty() {
    }


    @Override
    public BinNode insert(
        AirObject obj,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level) {
        BinLeaf leaf = new BinLeaf();
        return leaf.insert(obj, x, y, z, w, h, d, level);
    }


    @Override
    public BinNode delete(
        AirObject obj,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
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


    @Override
    public void collisions(
        StringBuilder sb,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level) {
        // Empty nodes have no objects
    }


    @Override
    public int intersect(
        StringBuilder sb,
        int[] query,
        int x,
        int y,
        int z,
        int w,
        int h,
        int d,
        int level) {
        if (!intersects(x, y, z, w, h, d, query[0], query[1], query[2],
            query[3], query[4], query[5])) {
            return 0;
        }
// sb.append("E (").append(x).append(", ").append(y).append(", ").append(z)
// .append(", ").append(w).append(", ").append(h).append(", ").append(
// d).append(") ").append(level).append("\n");
        return 1;
    }


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
